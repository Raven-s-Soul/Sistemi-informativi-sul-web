package it.uniroma3.siw.controller;

import java.util.List;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import it.uniroma3.siw.model.entities.Author;
import it.uniroma3.siw.model.entities.Book;
import it.uniroma3.siw.model.entities.Image;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;

@Controller
public class ImageController {
	
	@Autowired
	public AuthorService authorService;
	
	@Autowired
	public BookService bookService;
	
	@GetMapping("/image/author/{authorId}")
	@ResponseBody
	public ResponseEntity<byte[]> getAuthorImage(@PathVariable Long authorId) {
	    Author author = authorService.getAuthorById(authorId);
	    if (author == null || author.getFoto() == null || author.getFoto().getImage() == null) {
	        return ResponseEntity.notFound().build();
	    }

	    byte[] imageBytes = author.getFoto().getImage();

	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG)
	            .body(imageBytes);
	}


	@GetMapping("/image/book/{bookId}/{imageIndex}")
	@ResponseBody
	public ResponseEntity<byte[]> getBookImageByIndex(
	        @PathVariable Long bookId,
	        @PathVariable int imageIndex) {

	    Book book = bookService.getBookById(bookId);
	    if (book == null || book.getImmagini() == null || book.getImmagini().isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    List<Image> images = book.getImmagini();

	    if (imageIndex < 0 || imageIndex >= images.size()) {
	        return ResponseEntity.badRequest().build();
	    }

	    byte[] imageBytes = images.get(imageIndex).getImage();

	    if (imageBytes == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG)
	            .body(imageBytes);
	}
	
}
