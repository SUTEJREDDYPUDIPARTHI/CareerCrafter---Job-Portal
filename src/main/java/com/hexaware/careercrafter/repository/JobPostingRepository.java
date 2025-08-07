package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.entities.JobPosting;
import com.hexaware.careercrafter.entities.JobPosting.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
	List<JobPosting> findByEmployerEmployerId(int employerId);
	List<JobPosting> findByIsActiveTrue();
	List<JobPosting> findByJobType(JobType type);


}
