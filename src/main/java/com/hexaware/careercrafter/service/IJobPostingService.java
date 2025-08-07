package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.*;
import java.util.List;

public interface IJobPostingService {
	
	JobPosting createJobPosting(JobPosting jobPosting);
    JobPosting getJobPostingById(int id);
    List<JobPosting> getAllJobPostings();
    List<JobPosting> getActiveJobPostings();
    List<JobPosting> getJobPostingsByEmployerId(int employerId);
    JobPosting updateJobPosting(JobPosting jobPosting);
    void deleteJobPosting(int id);

}
