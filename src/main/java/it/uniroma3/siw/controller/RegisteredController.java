package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisteredController {

	@GetMapping("/registered")
	public String showRegistered(Model model) {
	    return "registered/registered";
	}
}
