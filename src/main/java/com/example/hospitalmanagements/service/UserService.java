package com.example.hospitalmanagements.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.User;
import com.example.hospitalmanagements.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user.
     * @return An Optional containing the user if found, or empty if not found.
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Saves a user to the repository.
     *
     * @param user The user to save.
     */
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Authenticates a user based on their username and password.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return A String indicating the result of the login attempt.
     */
    public String loginUser(String username, String password) {
        Optional<User> optionalUser = findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Check if the provided password matches the stored password
            if (user.getPassword().equals(password)) {
                return "Success";
            } else {
                return "Invalid username or password";
            }
        } else {
            return "Invalid username or password";
        }
    }

    /**
     * Registers a new user if the username is not already taken.
     *
     * @param user The user to register.
     * @return A String indicating the result of the registration attempt.
     */
    public String registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "User already exists";
        }

        userRepository.save(user);
        return "Success";
    }

    /**
     * Finds the department of a user by their username.
     *
     * @param username The username of the user.
     * @return An Optional containing the department if the user is found, or empty if not found.
     */
    public Optional<String> findDepartmentByUsername(String username) {
        return userRepository.findByUsername(username)
                             .map(User::getDepartment);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    public User getUserById(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
    }
    public void updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                             .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setDepartment(updatedUser.getDepartment());
        userRepository.save(existingUser);
    }
}
