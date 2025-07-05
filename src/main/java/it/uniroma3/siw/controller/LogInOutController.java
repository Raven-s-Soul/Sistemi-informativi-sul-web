package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.entities.Credentials;
import it.uniroma3.siw.model.entities.User;
import it.uniroma3.siw.model.enums.UserRole;
import it.uniroma3.siw.service.CredentialsService;
//import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class LogInOutController {
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String showloging(@RequestParam(required = false) boolean error, Model model) {

	    if (error)
	        model.addAttribute("Error", "Username o password errati");

	    return "login";
	}
	


	@GetMapping("/register")
	public String showRegistration(Model model) {
	    Credentials credentials = new Credentials();
	    credentials.setUtente(new User()); // This is essential

	    model.addAttribute("credentials", credentials);
	    return "register";
	}
	
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute Credentials credentials,
            BindingResult credentialsBindingResult, @RequestParam String repassword, Model model) {

    	User utente = credentials.getUtente();
        
        // Validation check for both objects
        if (credentialsBindingResult.hasErrors()) {
            model.addAttribute("msgError", "Errore nella validazione dei dati");
            return "register";
        }
        // Check if username already exists
        if (credentialsService.existsByUsername(credentials.getUsername())) {
            model.addAttribute("msgError", "Username già in uso");
            return "register";
        }
        if(!credentials.getPassword().equals(repassword)) {
        	model.addAttribute("msgError", "Le 2 password scritte non sono uguali");
            return "register";
        }
        try {
        	credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        	utente.setRole(UserRole.REGISTERED);
            credentials.setUtente(utente);
            
            // Save credentials (which will cascade to utente due to @OneToOne relationship)
            //userService.saveUser(utente);
            credentialsService.saveCredentials(credentials);
            
            model.addAttribute("msgSuccess", "Registrazione completata con successo");
            return "redirect:/utente/"+ utente.getId();
            
        } catch (Exception e) {
            model.addAttribute("msgError", "Errore durante la registrazione");
            return "register";
        }
    }
    
}
