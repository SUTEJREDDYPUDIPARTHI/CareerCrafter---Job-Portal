package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.UserDTO;
import com.hexaware.careercrafter.entities.User;
import com.hexaware.careercrafter.exception.*;
import com.hexaware.careercrafter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getEmail() == null || userDTO.getPassword() == null || userDTO.getName() == null) {
            throw new InvalidRequestException("Name, Email, and Password are required.");
        }

        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new DuplicateResourceException("Email already exists.");
        }

        User savedUser = userRepository.save(convertToEntity(userDTO));
        return convertToDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found."));
        return convertToDTO(user);
    }

    @Override
    public void deleteUser(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with ID " + userId + " not found.");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        if (userDTO.getUserId() <= 0 || userDTO.getName() == null || userDTO.getEmail() == null) {
            throw new InvalidRequestException("User ID, Name, and Email are required for update.");
        }

        userRepository.findById(userDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userDTO.getUserId() + " not found."));

        User existingByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (existingByEmail != null && existingByEmail.getUserId() != userDTO.getUserId()) {
            throw new DuplicateResourceException("Email already in use by another user.");
        }

        User updatedUser = userRepository.save(convertToEntity(userDTO));
        return convertToDTO(updatedUser);
    }

    // ---------------------- Mapping helpers ----------------------

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUserType(dto.getUserType());
        return user;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setUserType(user.getUserType());
        return dto;
    }
}
