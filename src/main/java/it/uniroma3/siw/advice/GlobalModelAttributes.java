package it.uniroma3.siw.advice;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siw.model.entities.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;

@ControllerAdvice
public class GlobalModelAttributes {

	@Autowired
    private  UserService userService;
	
	@Autowired
    private  CredentialsService credentialService;

    @ModelAttribute
    public void addUserToModel(Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            String username = credentialService.getUsernameByUserId(user.getId());
            model.addAttribute("user", user);
            model.addAttribute("username", username);
        }
        
        
        //model.addAttribute();
    }
}
