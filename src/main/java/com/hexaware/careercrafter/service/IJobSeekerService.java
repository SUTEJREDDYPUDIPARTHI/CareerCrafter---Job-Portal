package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.*;
import java.util.List;

public interface IJobSeekerService {
	
	JobSeeker createJobSeeker(JobSeeker jobSeeker);
    JobSeeker getJobSeekerById(int id);
    List<JobSeeker> getAllJobSeekers();
    JobSeeker updateJobSeeker(JobSeeker jobSeeker);
    void deleteJobSeeker(int id);

}
