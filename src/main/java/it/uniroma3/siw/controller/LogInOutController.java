package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import jakarta.validation.Valid;

@Controller
public class LogInOutController {
	@Autowired
	private CredentialsService credentialsService;
	
	@GetMapping("/login")
	public String showloging(@RequestParam(required = false) boolean error, Model model) {

	    if (error)
	        model.addAttribute("Error", "Username o password errati");

	    return "login";
	}

	@GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("utente", new User());
        model.addAttribute("credentials", new Credentials());
        return "register";
    }
	
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User utente,
                             BindingResult utenteBindingResult,
                             @Valid @ModelAttribute Credentials credentials,
                             @Valid @RequestParam String confermaPwd,
                             BindingResult credentialsBindingResult,
                             Model model) {
        
        // Validation check for both objects
        if (utenteBindingResult.hasErrors() || credentialsBindingResult.hasErrors()) {
            model.addAttribute("msgError", "Errore nella validazione dei dati");
            return "register";
        }
        // Check if username already exists
        if (credentialsService.existsByUsername(credentials.getUsername())) {
            model.addAttribute("msgError", "Username già in uso");
            return "register";
        }
        if(!credentials.getPassword().equals(confermaPwd)) {
        	model.addAttribute("msgError", "Le 2 password scritte non sono uguali");
            return "register";
        }
        try {
        	utente.setRole(UserRole.REGISTERED);
            // Link utente to credentials
            credentials.setUtente(utente);
            
            // Save credentials (which will cascade to utente due to @OneToOne relationship)
            credentialsService.saveCredentials(credentials);
            
            model.addAttribute("msgSuccess", "Registrazione completata con successo");
            return "redirect:/utente/"+ utente.getId();
            
        } catch (Exception e) {
            model.addAttribute("Error", "Errore durante la registrazione");
            return "register";
        }
    }
    
}
