package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public String showAdmin(Model model) {

	    model.addAttribute("message", "Welcome to the home page!");
	    return "admin/admin";
	}
}
