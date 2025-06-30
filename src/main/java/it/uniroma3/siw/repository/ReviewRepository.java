package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.entities.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
