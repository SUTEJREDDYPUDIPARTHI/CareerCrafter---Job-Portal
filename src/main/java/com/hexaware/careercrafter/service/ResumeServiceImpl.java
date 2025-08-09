package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.ResumeDTO;
import com.hexaware.careercrafter.entities.JobSeeker;
import com.hexaware.careercrafter.entities.Resume;
import com.hexaware.careercrafter.exception.InvalidRequestException;
import com.hexaware.careercrafter.exception.ResourceNotFoundException;
import com.hexaware.careercrafter.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeServiceImpl implements IResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public ResumeDTO uploadResume(ResumeDTO dto) {
        if (dto.getFilePath() == null || dto.getJobSeekerId() == 0) {
            throw new InvalidRequestException("File path and associated job seeker are required.");
        }
        Resume entity = mapToEntity(dto);
        Resume saved = resumeRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public ResumeDTO getResumeById(int id) {
        Resume entity = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found with ID: " + id));
        return mapToDTO(entity);
    }

    @Override
    public List<ResumeDTO> getResumesByJobSeekerId(int jobSeekerId) {
        List<Resume> resumes = resumeRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
        List<ResumeDTO> dtos = new ArrayList<>();
        for (Resume resume : resumes) {
            dtos.add(mapToDTO(resume));
        }
        return dtos;
    }

    @Override
    public void deleteResume(int id) {
        if (!resumeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Resume not found with ID: " + id);
        }
        resumeRepository.deleteById(id);
    }

    @Override
    public ResumeDTO updateResume(ResumeDTO dto) {
        if (!resumeRepository.existsById(dto.getResumeId())) {
            throw new ResourceNotFoundException("Cannot update. Resume not found with ID: " + dto.getResumeId());
        }
        Resume entity = mapToEntity(dto);
        Resume saved = resumeRepository.save(entity);
        return mapToDTO(saved);
    }

    // --- Mapping methods ---

    private ResumeDTO mapToDTO(Resume entity) {
        ResumeDTO dto = new ResumeDTO();
        dto.setResumeId(entity.getResumeId());
        dto.setJobSeekerId(entity.getJobSeeker() != null ? entity.getJobSeeker().getJobSeekerId() : 0);
        dto.setFilePath(entity.getFilePath());
        dto.setPrimary(entity.isPrimary());
        return dto;
    }

    private Resume mapToEntity(ResumeDTO dto) {
        Resume entity = new Resume();
        entity.setResumeId(dto.getResumeId());

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setJobSeekerId(dto.getJobSeekerId());
        entity.setJobSeeker(jobSeeker);

        entity.setFilePath(dto.getFilePath());
        entity.setPrimary(dto.isPrimary());
        return entity;
    }
}
