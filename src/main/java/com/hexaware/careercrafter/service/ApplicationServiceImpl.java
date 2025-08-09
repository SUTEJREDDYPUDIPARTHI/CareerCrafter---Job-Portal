package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.ApplicationDTO;
import com.hexaware.careercrafter.entities.Application;
import com.hexaware.careercrafter.entities.JobPosting;
import com.hexaware.careercrafter.entities.JobSeeker;
import com.hexaware.careercrafter.entities.Application.ApplicationStatus;
import com.hexaware.careercrafter.exception.InvalidRequestException;
import com.hexaware.careercrafter.exception.ResourceNotFoundException;
import com.hexaware.careercrafter.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public ApplicationDTO applyForJob(ApplicationDTO dto) {
        if (dto.getJobPostingId() == 0 || dto.getJobSeekerId() == 0) {
            throw new InvalidRequestException("JobPostingId and JobSeekerId must be provided.");
        }
        Application entity = mapToEntity(dto);
        Application saved = applicationRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public ApplicationDTO getApplicationById(int id) {
        Application entity = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + id));
        return mapToDTO(entity);
    }

    @Override
    public List<ApplicationDTO> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        List<ApplicationDTO> dtos = new ArrayList<>();
        for (Application app : applications) {
            dtos.add(mapToDTO(app));
        }
        return dtos;
    }

    @Override
    public List<ApplicationDTO> getApplicationsByJobSeekerId(int jobSeekerId) {
        List<Application> applications = applicationRepository.findByJobSeekerJobSeekerId(jobSeekerId);
        List<ApplicationDTO> dtos = new ArrayList<>();
        for (Application app : applications) {
            dtos.add(mapToDTO(app));
        }
        return dtos;
    }

    @Override
    public ApplicationDTO updateApplication(ApplicationDTO dto) {
        if (!applicationRepository.existsById(dto.getApplicationId())) {
            throw new ResourceNotFoundException("Application with ID " + dto.getApplicationId() + " not found for update.");
        }
        Application entity = mapToEntity(dto);
        Application saved = applicationRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public void deleteApplication(int id) {
        if (!applicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Application with ID " + id + " not found for deletion.");
        }
        applicationRepository.deleteById(id);
    }

    // --- Mapping methods ---

    private ApplicationDTO mapToDTO(Application entity) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setApplicationId(entity.getApplicationId());
        dto.setJobPostingId(entity.getJob() != null ? entity.getJob().getJobPostingId() : 0);
        dto.setJobSeekerId(entity.getJobSeeker() != null ? entity.getJobSeeker().getJobSeekerId() : 0);
        dto.setStatus(ApplicationDTO.ApplicationStatus.valueOf(entity.getStatus().name()));
        dto.setCoverLetter(entity.getCoverLetter());
        dto.setResumeFilePath(entity.getResumeFilePath());
        return dto;
    }

    private Application mapToEntity(ApplicationDTO dto) {
        Application entity = new Application();
        entity.setApplicationId(dto.getApplicationId());

        JobPosting jobPosting = new JobPosting();
        jobPosting.setJobPostingId(dto.getJobPostingId());
        entity.setJob(jobPosting);

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setJobSeekerId(dto.getJobSeekerId());
        entity.setJobSeeker(jobSeeker);

        entity.setStatus(ApplicationStatus.valueOf(dto.getStatus().name()));
        entity.setCoverLetter(dto.getCoverLetter());
        entity.setResumeFilePath(dto.getResumeFilePath());

        return entity;
    }
}
