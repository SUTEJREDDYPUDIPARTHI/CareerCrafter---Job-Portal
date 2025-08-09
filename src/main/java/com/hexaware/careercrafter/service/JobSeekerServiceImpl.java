package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.JobSeekerDTO;
import com.hexaware.careercrafter.entities.JobSeeker;
import com.hexaware.careercrafter.entities.User;
import com.hexaware.careercrafter.exception.InvalidRequestException;
import com.hexaware.careercrafter.exception.ResourceNotFoundException;
import com.hexaware.careercrafter.repository.JobSeekerRepository;
import com.hexaware.careercrafter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobSeekerServiceImpl implements IJobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JobSeekerDTO createJobSeeker(JobSeekerDTO dto) {
        if (dto.getFirstName() == null || dto.getLastName() == null || dto.getUserId() <= 0) {
            throw new InvalidRequestException("First name, last name, and valid userId are required.");
        }
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getUserId()));

        JobSeeker jobSeeker = convertToEntity(dto, user);
        JobSeeker saved = jobSeekerRepository.save(jobSeeker);
        return convertToDTO(saved);
    }

    @Override
    public List<JobSeekerDTO> getAllJobSeekers() {
        return jobSeekerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobSeekerDTO getJobSeekerById(int id) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job seeker not found with ID: " + id));
        return convertToDTO(jobSeeker);
    }

    @Override
    public void deleteJobSeeker(int id) {
        if (!jobSeekerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. JobSeeker not found with ID: " + id);
        }
        jobSeekerRepository.deleteById(id);
    }

    @Override
    public JobSeekerDTO updateJobSeeker(JobSeekerDTO dto) {
        if (!jobSeekerRepository.existsById(dto.getJobSeekerId())) {
            throw new ResourceNotFoundException("Cannot update. JobSeeker not found with ID: " + dto.getJobSeekerId());
        }
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getUserId()));

        JobSeeker updatedEntity = convertToEntity(dto, user);
        JobSeeker saved = jobSeekerRepository.save(updatedEntity);
        return convertToDTO(saved);
    }

    // Convert DTO → Entity
    private JobSeeker convertToEntity(JobSeekerDTO dto, User user) {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setJobSeekerId(dto.getJobSeekerId());
        jobSeeker.setUser(user);
        jobSeeker.setFirstName(dto.getFirstName());
        jobSeeker.setLastName(dto.getLastName());
        jobSeeker.setPhone(dto.getPhone());
        jobSeeker.setAddress(dto.getAddress());
        jobSeeker.setEducation(dto.getEducation());
        jobSeeker.setSkills(dto.getSkills());
        jobSeeker.setExperience(dto.getExperience());
        return jobSeeker;
    }

    // Convert Entity → DTO
    private JobSeekerDTO convertToDTO(JobSeeker jobSeeker) {
        JobSeekerDTO dto = new JobSeekerDTO();
        dto.setJobSeekerId(jobSeeker.getJobSeekerId());
        dto.setUserId(jobSeeker.getUser().getUserId());
        dto.setFirstName(jobSeeker.getFirstName());
        dto.setLastName(jobSeeker.getLastName());
        dto.setPhone(jobSeeker.getPhone());
        dto.setAddress(jobSeeker.getAddress());
        dto.setEducation(jobSeeker.getEducation());
        dto.setSkills(jobSeeker.getSkills());
        dto.setExperience(jobSeeker.getExperience());
        return dto;
    }
}
