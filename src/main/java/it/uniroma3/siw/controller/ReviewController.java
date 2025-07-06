package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.entities.Book;
import it.uniroma3.siw.model.entities.Image;
import it.uniroma3.siw.model.entities.Review;
import it.uniroma3.siw.model.entities.User;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.ImageService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;
    
    @Autowired
    private ImageService imagesService;
    
    @Autowired
    private UserService userService;
    
	// === GET Routes ===

    // List all reviews
	@GetMapping("/list")
	public String listReviews(Model model) {
	    List<Book> books = (List<Book>) bookService.getAllBooks();
	    List<Review> reviews = (List<Review>) reviewService.getAllReviews();

	    Map<Long, List<Review>> reviewMap = new HashMap<>();
	    for (Review r : reviews) {
	        Long bookId = r.getBook().getId();
	        reviewMap.computeIfAbsent(bookId, k -> new ArrayList<>()).add(r);
	    }

	    model.addAttribute("books", books);
	    model.addAttribute("reviewMap", reviewMap);

	    return "review/list";
	}


    // Show form to select book for review
    @GetMapping("/select")
    public String selectBook(Model model) {
    	 model.addAttribute("reviews", reviewService.getAllReviews());
         model.addAttribute("books", bookService.getAllBooks());
         
         Map<Long,Image> map = new HashMap<>();
         for(Image i : imagesService.findAll()) {
         	if(i.getBook() != null && !map.containsKey(i.getBook().getId())){
         		map.put(i.getBook().getId(),i);
         	}
         }
         model.addAttribute("map", map);
         
         return "review/selector";  // -> templates/review/list.html
    }
    
    // Show form to add a review for a specific book
    @GetMapping("/add/{bookId}")
    public String showAddForm(@PathVariable Long bookId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Book book = bookService.getBookById(bookId);
        Review review = new Review();
        review.setBook(book);
        
        User user = userService.findByUsername(userDetails.getUsername());
        Optional<Review> existingReview = reviewService.findByBookAndUser(book, user);
        
        if (existingReview.isPresent()) {
            // Reindirizza alla pagina di modifica della recensione esistente
            return "redirect:/review/edit/" + existingReview.get().getId();
        }

        model.addAttribute("review", review);
        model.addAttribute("book", book);
        return "review/add";  // -> templates/review/add.html
    }
    
    // Save new review
    @PostMapping("/add/{bookId}")
    public String addReview(@PathVariable Long bookId, @Valid @ModelAttribute Review review,
                            BindingResult result, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            model.addAttribute("book", bookService.getBookById(bookId));
            return "review/add";
        }
        Book book = bookService.getBookById(bookId);
        review.setBook(book);
        
        User user = userService.findByUsername(userDetails.getUsername());
        review.setUser(user);
        
        reviewService.saveReview(review);
        return "redirect:/review/list";
    }

    @GetMapping("/edit")
    public String listUserReviewsForEdit(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User currentUser = userService.findByUsername(userDetails.getUsername());
        List<Review> userReviews = reviewService.getReviewsByUser(currentUser);
        model.addAttribute("reviews", userReviews);
        return "review/edit";  // pagina per scegliere quale modificare
    }

 // Mostra il form per modificare una review esistente
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Review review = reviewService.getReviewById(id);
        model.addAttribute("review", review);
        return "review/editForm";  // -> template per il form di edit
    }

    @PostMapping("/edit/{id}")
    public String editReview(@PathVariable Long id, @Valid @ModelAttribute Review review,
                             BindingResult result, @RequestParam Long bookId, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "review/edit";
        }
        User user = userService.findByUsername(userDetails.getUsername());
        review.setUser(user);
        Book book = bookService.getBookById(bookId);
        review.setBook(book);
        review.setId(id);
        reviewService.saveReview(review);
        return "redirect:/review/edit";
    }


    @GetMapping("/delete")
    public String showDeleteReview(Model model) {
    	List<Book> books = (List<Book>) bookService.getAllBooks();
	    List<Review> reviews = (List<Review>) reviewService.getAllReviews();

	    Map<Long, List<Review>> reviewMap = new HashMap<>();
	    for (Review r : reviews) {
	        Long bookId = r.getBook().getId();
	        reviewMap.computeIfAbsent(bookId, k -> new ArrayList<>()).add(r);
	    }

	    model.addAttribute("books", books);
	    model.addAttribute("reviewMap", reviewMap);
        
        return "review/delete";
    }
    
    // Delete review
    @PostMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReviewById(id);
        return "redirect:/review/delete";
    }
}
