package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.JobPosting;
import com.hexaware.careercrafter.exception.InvalidRequestException;
import com.hexaware.careercrafter.exception.ResourceNotFoundException;
import com.hexaware.careercrafter.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingServiceImpl implements IJobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Override
    public JobPosting createJobPosting(JobPosting jobPosting) {
        if (jobPosting.getEmployer() == null || jobPosting.getTitle() == null || jobPosting.getDescription() == null) {
            throw new InvalidRequestException("Employer, title, and description must be provided.");
        }
        return jobPostingRepository.save(jobPosting);
    }

    @Override
    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    @Override
    public JobPosting getJobPostingById(int id) {
        return jobPostingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobPosting not found with ID: " + id));
    }

    @Override
    public void deleteJobPosting(int id) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete. JobPosting with ID " + id + " not found."));
        jobPosting.setActive(false); // Soft delete
        jobPostingRepository.save(jobPosting);
    }

    @Override
    public JobPosting updateJobPosting(JobPosting jobPosting) {
        if (!jobPostingRepository.existsById(jobPosting.getJobPostingId())) {
            throw new ResourceNotFoundException("Cannot update. JobPosting with ID " + jobPosting.getJobPostingId() + " not found.");
        }
        return jobPostingRepository.save(jobPosting);
    }

    @Override
    public List<JobPosting> getActiveJobPostings() {
        return jobPostingRepository.findByIsActiveTrue();
    }

    @Override
    public List<JobPosting> getJobPostingsByEmployerId(int employerId) {
        return jobPostingRepository.findByEmployerEmployerId(employerId);
    }
}
