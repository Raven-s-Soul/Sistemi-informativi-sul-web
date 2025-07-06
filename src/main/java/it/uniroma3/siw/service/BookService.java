package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.entities.Book;
import it.uniroma3.siw.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Retrieves a Book by its ID.
     * 
     * @param id the ID of the book
     * @return the Book if found, otherwise null
     */
    public Book getBookById(Long id) {
        Optional<Book> result = bookRepository.findById(id);
        return result.orElse(null); // or throw a custom NotFoundException
    }

    /**
     * Returns all books in the repository.
     * 
     * @return Iterable of all books
     */
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Saves a new or existing book to the repository.
     * 
     * @param book the Book entity to be saved
     * @return the saved Book
     */
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Deletes a book by its ID.
     * 
     * @param id the ID of the book to delete
     */
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    /**
     * Checks if a book exists by its ID.
     * 
     * @param id the ID to check
     * @return true if the book exists, false otherwise
     */
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

	public Optional<Book> findById(Long id) {
		  return bookRepository.findById(id);
	}
}
