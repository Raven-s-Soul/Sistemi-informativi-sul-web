package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.entities.Author;
import it.uniroma3.siw.model.entities.Image;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.ImageService;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    
    

    // Show all authors
    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author/list";
    }

    @GetMapping("/add")
    public String showAddAuthorForm(Model model) {
        Author author = new Author();
        Image img = new Image();
        img.setAutore(author);
        author.setFoto(img);
        model.addAttribute("author", author);
        return "author/add";
    }

    @PostMapping("/add")
    public String addAuthor(
        @Valid @ModelAttribute Author author,
        BindingResult bindingResult,
        Model model,
        @RequestParam(required = false) MultipartFile imageFile) throws IOException {

        if (bindingResult.hasErrors()) {
            return "author/add";
        }

        if (imageFile != null && !imageFile.isEmpty()) {
        	Image img = new Image();
        	img.setAutore(author);
            img.setImage(imageFile.getBytes());
            
            author.setFoto(img);
        }
        
        authorService.saveAuthor(author);
        return "redirect:/author";
    }


    // Show form to edit an existing author
    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable Long id, Model model) {
        Optional<Author> optionalAuthor = Optional.of(authorService.getAuthorById(id));
        
        if (optionalAuthor.isPresent()) {
            model.addAttribute("author", optionalAuthor.get());
            return "author/edit";
        } else {
            // Handle author not found, maybe redirect or return an error page
            return "redirect:/author/edit"; // or some error page
        }
    }

    // Handle edit submission
    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id, @Valid @ModelAttribute Author author,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "author/edit";
        }
        author.setId(id);
        authorService.saveAuthor(author);
        return "redirect:/author";
    }

    // Delete an author
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return "redirect:/author";
    }
}
