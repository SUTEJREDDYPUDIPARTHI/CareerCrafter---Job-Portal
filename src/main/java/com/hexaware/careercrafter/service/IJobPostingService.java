package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.*;
import java.util.List;

public interface IJobPostingService {

    JobPostingDTO createJobPosting(JobPostingDTO jobPostingDTO);
    JobPostingDTO getJobPostingById(int id);
    List<JobPostingDTO> getAllJobPostings();
    List<JobPostingDTO> getActiveJobPostings();
    List<JobPostingDTO> getJobPostingsByEmployerId(int employerId);
    JobPostingDTO updateJobPosting(JobPostingDTO jobPostingDTO);
    void deleteJobPosting(int id);
}

