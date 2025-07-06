package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.entities.Book;
import it.uniroma3.siw.model.entities.Review;
import it.uniroma3.siw.model.entities.User;
import it.uniroma3.siw.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Retrieves a Review by its ID.
     * 
     * @param id the ID of the review
     * @return the Review if found, otherwise null
     */
    public Review getReviewById(Long id) {
        Optional<Review> result = reviewRepository.findById(id);
        return result.orElse(null); // or throw custom NotFoundException
    }

    /**
     * Returns all reviews in the repository.
     * 
     * @return Iterable of all reviews
     */
    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Saves a new or existing review.
     * 
     * @param review the Review entity to be saved
     * @return the saved Review
     */
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Deletes a review by its ID.
     * 
     * @param id the ID of the review to delete
     */
    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }

    /**
     * Checks if a review exists by its ID.
     * 
     * @param id the ID to check
     * @return true if the review exists, false otherwise
     */
    public boolean existsById(Long id) {
        return reviewRepository.existsById(id);
    }

    public List<Review> getReviewsByUser(User currentUser) {
        return reviewRepository.findByUser(currentUser);
    }

	public Optional<Review> findByBookAndUser(Book book, User user) {
        return reviewRepository.findByBookAndUser(book, user);
	}

}
