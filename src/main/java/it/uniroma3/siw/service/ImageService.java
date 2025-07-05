package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.model.entities.Image;
import it.uniroma3.siw.repository.ImageRepository;

public class ImageService {

	@Autowired
    private ImageRepository imageRepository;
	
	public Iterable<Image> findAllImages() {
        return imageRepository.findAll();
    }
}
