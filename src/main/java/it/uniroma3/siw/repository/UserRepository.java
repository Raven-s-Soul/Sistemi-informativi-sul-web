package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.entities.User;

@SuppressWarnings("unused")
public interface UserRepository extends JpaRepository<User, Long> {
    
}
