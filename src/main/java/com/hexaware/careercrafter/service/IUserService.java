package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.UserDTO;
import java.util.List;

public interface IUserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(int userId);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(int userId);
}
