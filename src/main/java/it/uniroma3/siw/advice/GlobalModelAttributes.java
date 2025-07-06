package it.uniroma3.siw.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public void addUserToModel(Model model, @AuthenticationPrincipal UserDetails userDetails, Authentication authentication) {
        if (userDetails != null) {
            User user = userService.findByUsername(userDetails.getUsername());
            String username = credentialService.getUsernameByUserId(user.getId());
            model.addAttribute("user", user);
            model.addAttribute("username", username);
        }
        
    }
}
