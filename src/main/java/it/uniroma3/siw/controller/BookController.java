package it.uniroma3.siw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.model.entities.Book;
import it.uniroma3.siw.service.BookService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // Show form to add a new book
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    // Handle form submission for adding a new book
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book/add";
        }
        bookService.saveBook(book);
        return "redirect:/admin"; // Redirect to list or wherever you want
    }

    // Show form to edit a book by ID
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isEmpty()) {
            return "redirect:/books"; // or show error page
        }
        model.addAttribute("book", book.get());
        return "book/edit"; // Thymeleaf template for edit form
    }

    // Handle form submission for editing a book
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book/edit";
        }
        book.setId(id); // Ensure the book ID is set for update
        bookService.saveBook(book);
        return "redirect:/books";
    }

    // Delete a book by ID
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}

