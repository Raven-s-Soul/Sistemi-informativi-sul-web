package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.entities.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;
import jakarta.validation.Valid;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    public void saveCredentials(@Valid Credentials credentials) {
        credentialsRepository.save(credentials);
    }

    public boolean existsByUsername(String username) {
        return credentialsRepository.existsByUsername(username);
    }
}
