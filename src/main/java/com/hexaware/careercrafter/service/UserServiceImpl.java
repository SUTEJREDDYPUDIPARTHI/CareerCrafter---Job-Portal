package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.*;
import com.hexaware.careercrafter.repository.*;
import com.hexaware.careercrafter.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null || user.getName() == null) {
            throw new InvalidRequestException("Name, Email, and Password are required.");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicateResourceException("Email already exists.");
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found."));
    }

    @Override
    public void deleteUser(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with ID " + userId + " not found.");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        if (user.getUserId() <= 0 || user.getName() == null || user.getEmail() == null) {
            throw new InvalidRequestException("User ID, Name and Email are required for update.");
        }

        userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + user.getUserId() + " not found."));

        User userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail != null && userByEmail.getUserId() != user.getUserId()) {
            throw new DuplicateResourceException("Email already in use by another user.");
        }

        return userRepository.save(user);
    }
}
