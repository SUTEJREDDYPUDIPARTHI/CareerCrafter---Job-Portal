package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.JobSeeker;
import com.hexaware.careercrafter.repository.JobSeekerRepository;
import com.hexaware.careercrafter.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerServiceImpl implements IJobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Override
    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        if (jobSeeker.getFirstName() == null || jobSeeker.getLastName() == null || jobSeeker.getUser() == null) {
            throw new InvalidRequestException("First name, last name, and associated user are required.");
        }
        return jobSeekerRepository.save(jobSeeker);
    }

    @Override
    public List<JobSeeker> getAllJobSeekers() {
        return jobSeekerRepository.findAll();
    }

    @Override
    public JobSeeker getJobSeekerById(int id) {
        return jobSeekerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Job seeker not found with ID: " + id));
    }

    @Override
    public void deleteJobSeeker(int id) {
        if (!jobSeekerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. JobSeeker not found with ID: " + id);
        }
        jobSeekerRepository.deleteById(id);
    }

    @Override
    public JobSeeker updateJobSeeker(JobSeeker jobSeeker) {
        if (!jobSeekerRepository.existsById(jobSeeker.getJobSeekerId())) {
            throw new ResourceNotFoundException("Cannot update. JobSeeker not found with ID: " + jobSeeker.getJobSeekerId());
        }
        return jobSeekerRepository.save(jobSeeker);
    }
}
