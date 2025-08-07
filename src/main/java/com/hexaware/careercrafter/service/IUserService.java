package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.*;
import java.util.List;

public interface IUserService {
	
	User createUser(User user);
	User getUserById(int userId);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(int userId);

}
