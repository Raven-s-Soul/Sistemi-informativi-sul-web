package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.model.entities.Book;
import it.uniroma3.siw.model.entities.Review;
import it.uniroma3.siw.model.entities.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByUser(User currentUser);

	Optional<Review> findByBookAndUser(Book book, User user);

}
