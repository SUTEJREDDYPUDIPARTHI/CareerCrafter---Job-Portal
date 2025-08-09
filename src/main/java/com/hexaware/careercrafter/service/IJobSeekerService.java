package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.*;
import java.util.List;

public interface IJobSeekerService {
    JobSeekerDTO createJobSeeker(JobSeekerDTO jobSeekerDTO);
    JobSeekerDTO getJobSeekerById(int id);
    List<JobSeekerDTO> getAllJobSeekers();
    JobSeekerDTO updateJobSeeker(JobSeekerDTO jobSeekerDTO);
    void deleteJobSeeker(int id);
}

