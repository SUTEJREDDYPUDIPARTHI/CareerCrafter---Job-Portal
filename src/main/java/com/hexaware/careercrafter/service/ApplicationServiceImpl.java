package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.Application;
import com.hexaware.careercrafter.repository.ApplicationRepository;
import com.hexaware.careercrafter.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Application applyForJob(Application application) {
        if (application.getJob() == null || application.getJobSeeker() == null) {
            throw new InvalidRequestException("Job and JobSeeker must be provided to apply.");
        }
        return applicationRepository.save(application);
    }

    @Override
    public Application getApplicationById(int id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + id));
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public List<Application> getApplicationsByJobSeekerId(int seekerId) {
        return applicationRepository.findByJobSeekerSeekerId(seekerId);
    }

    @Override
    public Application updateApplication(Application application) {
        if (!applicationRepository.existsById(application.getApplicationId())) {
            throw new ResourceNotFoundException("Application with ID " + application.getApplicationId() + " not found for update.");
        }
        return applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(int id) {
        if (!applicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Application with ID " + id + " not found for deletion.");
        }
        applicationRepository.deleteById(id);
    }
}
