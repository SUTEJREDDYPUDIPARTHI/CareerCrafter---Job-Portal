package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

}