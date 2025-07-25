package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.entities.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
