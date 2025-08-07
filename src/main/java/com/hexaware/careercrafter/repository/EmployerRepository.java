package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
	List<Employer> findByUserUserId(int userId);

}
