package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.Resume;
import com.hexaware.careercrafter.repository.ResumeRepository;
import com.hexaware.careercrafter.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements IResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public Resume uploadResume(Resume resume) {
        if (resume.getFilePath() == null || resume.getJobSeeker() == null) {
            throw new InvalidRequestException("File path and associated job seeker are required.");
        }
        return resumeRepository.save(resume);
    }

    @Override
    public Resume getResumeById(int id) {
        return resumeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Resume not found with ID: " + id));
    }

    @Override
    public List<Resume> getResumesByJobSeekerId(int jobSeekerId) {
        return resumeRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
    }

    @Override
    public void deleteResume(int id) {
        if (!resumeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Resume not found with ID: " + id);
        }
        resumeRepository.deleteById(id);
    }

    @Override
    public Resume updateResume(Resume resume) {
        if (!resumeRepository.existsById(resume.getResumeId())) {
            throw new ResourceNotFoundException("Cannot update. Resume not found with ID: " + resume.getResumeId());
        }
        return resumeRepository.save(resume);
    }
}
