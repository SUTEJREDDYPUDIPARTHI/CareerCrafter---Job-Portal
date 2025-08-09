package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByJobSeekerJobSeekerId(int jobSeekerId);
    List<Application> findByJob_JobPostingId(int jobPostingId);

}
