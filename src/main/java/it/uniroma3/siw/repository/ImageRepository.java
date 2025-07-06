package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.model.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
