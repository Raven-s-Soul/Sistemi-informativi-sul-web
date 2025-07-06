package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.entities.Author;
import it.uniroma3.siw.model.entities.Image;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import jakarta.validation.Valid;

import java.io.IOException;
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
    
    @SuppressWarnings("unused")
	@Autowired
    private BookService bookService;

	@GetMapping("/list")
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
        return "redirect:/author/list";
    }

    @GetMapping("/edit")
    public String showEditAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author/edit";
    }

    // Show form to edit an existing author
    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        
        if (author != null ) {
            model.addAttribute("author", author);
            // model.addAttribute("books", author.getBooks());
            return "author/editForm";
        } else {
            // Handle author not found, maybe redirect or return an error page
            return "redirect:/author/edit"; // or some error page
        }
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id,
                             @Valid @ModelAttribute Author author,
                             BindingResult bindingResult,
                             @RequestParam MultipartFile imageFile,
                             Model model) throws IOException {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("books", author.getBooks()); // In case you need them again in the form
            return "author/editForm"; // Correct path to the form
        }

        Author existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor == null) {
            return "redirect:/author/list";
        }

        // Update standard fields
        existingAuthor.setFirstName(author.getFirstName());
        existingAuthor.setLastName(author.getLastName());
        existingAuthor.setBirthDate(author.getBirthDate());
        existingAuthor.setDeathDate(author.getDeathDate());
        existingAuthor.setNationality(author.getNationality());

        // Check and update image only if uploaded
        if (imageFile != null && !imageFile.isEmpty()) {
            Image img = existingAuthor.getFoto();
            if (img == null) {
                img = new Image();
                img.setAutore(existingAuthor);
            }
            img.setImage(imageFile.getBytes());
            existingAuthor.setFoto(img);
        }

        authorService.saveAuthor(existingAuthor);
        return "redirect:/author/list";
    }

    
    @GetMapping("/delete")
    public String showDeleteAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author/delete";
    }

    // Delete an author
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return "redirect:/author/delete";
    }
}
