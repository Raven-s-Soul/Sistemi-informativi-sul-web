package it.uniroma3.siw.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.entities.Author;
import it.uniroma3.siw.model.entities.Book;
import it.uniroma3.siw.model.entities.Image;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.ImageService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private ImageService imagesService;


    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        Map<Long,List<Image>> map = new HashMap<>();
        for(Image i : imagesService.findAll()) {
        	List<Image> list = new ArrayList<>();
        	if(i.getBook() != null && !map.containsKey(i.getBook().getId())){
        		
        		list.add(i);
        		map.put(i.getBook().getId(),list);
        	}
        	if(i.getBook() != null && map.containsKey(i.getBook().getId())){
        		list = map.get(i.getBook().getId());
        		list.add(i);
        		map.put(i.getBook().getId(), list );
        		
        	}
        	/*
        	if(i.getBook() != null && i.getBook().getId() != null)
        		System.out.println(i.getBook().getId());
        	else
        		System.out.println("Null");
        	*/
        }
        model.addAttribute("map", map);
        return "book/list";
    }
    
    // Show form to add a new book
    @GetMapping("/add")
    public String showAddForm(Model model) {
    	Book book = new Book();
    	book.setPublicationYear(LocalDate.now().getYear());
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book,
                          BindingResult bindingResult,
                          @RequestParam List<MultipartFile> imageFiles,
                          @RequestParam(required = false) List<Long> authorIds, // <--- get author IDs here
                          Model model) {

        if (bindingResult.hasErrors()) {
            return "book/add";
        }

        // Set authors on book if any selected
        if (authorIds != null && !authorIds.isEmpty()) {
            List<Author> authors = (List<Author>) authorService.findAuthorsByIds(authorIds);
            book.setAuthors(authors);
        } else {
            book.setAuthors(new ArrayList<>());
        }

        // Process images
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            if (!file.isEmpty()) {
                try {
                    Image img = new Image();
                    img.setImage(file.getBytes());
                    img.setBook(book);
                    imageList.add(img);
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("error", "Error uploading image.");
                    return "book/add";
                }
            }
        }
        book.setImmagini(imageList);

        bookService.saveBook(book);
        return "redirect:/book/edit";
    }
    
    // Delete a book by ID
    @GetMapping("/edit")
    public String showEditBook(Model model) {
    	model.addAttribute("books", bookService.getAllBooks());
        Map<Long,List<Image>> map = new HashMap<>();
        for(Image i : imagesService.findAll()) {
        	List<Image> list = new ArrayList<>();
        	if(i.getBook() != null && !map.containsKey(i.getBook().getId())){
        		
        		list.add(i);
        		map.put(i.getBook().getId(),list);
        	}
        	if(i.getBook() != null && map.containsKey(i.getBook().getId())){
        		list = map.get(i.getBook().getId());
        		list.add(i);
        		map.put(i.getBook().getId(), list );
        		
        	}
        	/*
        	if(i.getBook() != null && i.getBook().getId() != null)
        		System.out.println(i.getBook().getId());
        	else
        		System.out.println("Null");
        	*/
        }
        model.addAttribute("map", map);
        return "book/edit";
    }
    
    // Show form to edit an existing author
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        
        if (book != null ) {
        	model.addAttribute("book", book);
        	model.addAttribute("authors", authorService.getAllAuthors());
            return "book/editForm";
        } else {
            // Handle author not found, maybe redirect or return an error page
            return "redirect:/book/edit"; // or some error page
        }
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,
                           @Valid @ModelAttribute Book book,
                           BindingResult result,
                           @RequestParam MultipartFile imageFile,
                           @RequestParam(required = false) List<Long> authorIds,
                           Model model) {

        if (result.hasErrors()) {
            // You may want to reload authors for the form in case of errors
            model.addAttribute("allAuthors", authorService.findAll());
            return "book/edit";
        }

        // Set the book ID to ensure update
        book.setId(id);

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                Image img = new Image();
                img.setImage(imageFile.getBytes());
                img.setBook(book);
                book.setImmagini(Collections.singletonList(img)); // oppure aggiorna la lista esistente
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Errore caricamento immagine.");
                return "book/edit";
            }
        }
        
        // Fetch authors by IDs and set them to the book
        if (authorIds != null && !authorIds.isEmpty()) {
            List<Author> selectedAuthors = (List<Author>) authorService.findAllById(authorIds);
            book.setAuthors(selectedAuthors);
        } else {
            book.setAuthors(new ArrayList<>());
        }

        bookService.saveBook(book);

        return "redirect:/book/list";
    }
    
    // Delete a book by ID
    @GetMapping("/delete")
    public String showDeleteBook(Model model) {
    	model.addAttribute("books", bookService.getAllBooks());
        Map<Long,List<Image>> map = new HashMap<>();
        for(Image i : imagesService.findAll()) {
        	List<Image> list = new ArrayList<>();
        	if(i.getBook() != null && !map.containsKey(i.getBook().getId())){
        		
        		list.add(i);
        		map.put(i.getBook().getId(),list);
        	}
        	if(i.getBook() != null && map.containsKey(i.getBook().getId())){
        		list = map.get(i.getBook().getId());
        		list.add(i);
        		map.put(i.getBook().getId(), list );
        		
        	}
        	/*
        	if(i.getBook() != null && i.getBook().getId() != null)
        		System.out.println(i.getBook().getId());
        	else
        		System.out.println("Null");
        	*/
        }
        model.addAttribute("map", map);
        return "book/delete";
    }

    // Delete a book by ID
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/book/delete";
    }
}

