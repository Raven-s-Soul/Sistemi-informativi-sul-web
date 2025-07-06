package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.entities.Author;
import it.uniroma3.siw.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Retrieves an Author by ID.
     *
     * @param id the ID of the author
     * @return the Author if found, otherwise null
     */
    public Author getAuthorById(Long id) {
        Optional<Author> result = authorRepository.findById(id);
        return result.orElse(null); // Consider throwing a custom NotFoundException
    }

    /**
     * Returns all authors in the repository.
     *
     * @return Iterable of authors
     */
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Saves a new or existing author to the repository.
     *
     * @param author the Author entity to be saved
     * @return the saved Author
     */
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    /**
     * Deletes an author by their ID.
     *
     * @param id the ID of the author to delete
     */
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    /**
     * Checks if an author exists by ID.
     *
     * @param id the ID to check
     * @return true if the author exists, false otherwise
     */
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

	public Object findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
