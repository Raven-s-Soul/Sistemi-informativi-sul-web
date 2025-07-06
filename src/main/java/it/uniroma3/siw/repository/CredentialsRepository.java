package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.model.entities.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    boolean existsByUsername(String username);
    Optional<Credentials> findByUsername(String username);
    
    Optional<Credentials> findByUtenteId(Long userId);
}
