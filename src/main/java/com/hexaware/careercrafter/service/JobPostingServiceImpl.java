package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.JobPostingDTO;
import com.hexaware.careercrafter.entities.Employer;
import com.hexaware.careercrafter.entities.JobPosting;
import com.hexaware.careercrafter.exception.InvalidRequestException;
import com.hexaware.careercrafter.exception.ResourceNotFoundException;
import com.hexaware.careercrafter.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPostingServiceImpl implements IJobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Override
    public JobPostingDTO createJobPosting(JobPostingDTO dto) {
        if (dto.getEmployerId() == 0 || dto.getTitle() == null || dto.getDescription() == null) {
            throw new InvalidRequestException("Employer, title, and description must be provided.");
        }
        JobPosting entity = mapToEntity(dto);
        JobPosting saved = jobPostingRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<JobPostingDTO> getAllJobPostings() {
        return jobPostingRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobPostingDTO getJobPostingById(int id) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobPosting not found with ID: " + id));
        return mapToDTO(jobPosting);
    }

    @Override
    public void deleteJobPosting(int id) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete. JobPosting with ID " + id + " not found."));
        jobPosting.setActive(false); // Soft delete by setting active to false
        jobPostingRepository.save(jobPosting);
    }

    @Override
    public JobPostingDTO updateJobPosting(JobPostingDTO dto) {
        if (!jobPostingRepository.existsById(dto.getJobPostingId())) {
            throw new ResourceNotFoundException("Cannot update. JobPosting with ID " + dto.getJobPostingId() + " not found.");
        }
        JobPosting updatedEntity = mapToEntity(dto);
        JobPosting saved = jobPostingRepository.save(updatedEntity);
        return mapToDTO(saved);
    }

    @Override
    public List<JobPostingDTO> getActiveJobPostings() {
        return jobPostingRepository.findByActiveTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobPostingDTO> getJobPostingsByEmployerId(int employerId) {
        return jobPostingRepository.findByEmployerEmployerId(employerId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private JobPostingDTO mapToDTO(JobPosting entity) {
        JobPostingDTO dto = new JobPostingDTO();
        dto.setJobPostingId(entity.getJobPostingId());
        dto.setEmployerId(entity.getEmployer() != null ? entity.getEmployer().getEmployerId() : 0);
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setLocation(entity.getLocation());
        dto.setJobType(entity.getJobType());
        dto.setActive(entity.isActive());
        return dto;
    }

    private JobPosting mapToEntity(JobPostingDTO dto) {
        JobPosting entity = new JobPosting();
        entity.setJobPostingId(dto.getJobPostingId());
        Employer employer = new Employer();
        employer.setEmployerId(dto.getEmployerId());
        entity.setEmployer(employer);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setJobType(dto.getJobType());
        entity.setActive(dto.isActive());
        return entity;
    }
}
