package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.entities.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
	List<Resume> findByJobSeeker_JobSeekerId(int jobSeekerId);

}
