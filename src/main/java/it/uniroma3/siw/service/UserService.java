package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.entities.User;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a User by their ID.
     * 
     * @param id the ID of the user
     * @return the User if found, otherwise null
     */
    public User getUserById(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null); // Consider throwing a custom NotFoundException
    }

    /**
     * Retrieves a User by their username.
     * 
     * @param username the username to search
     * @return the User if found, otherwise null
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * Returns all users.
     * 
     * @return Iterable of all users
     */
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Saves a new or existing user.
     * 
     * @param user the User entity to save
     * @return the saved User
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     * 
     * @param id the ID of the user to delete
     */
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Checks if a user exists by their ID.
     * 
     * @param id the ID to check
     * @return true if user exists, false otherwise
     */
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
