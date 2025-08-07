package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.entities.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {
	List<JobSeeker> findByFirstNameContainingIgnoreCase(String firstName);
	
}