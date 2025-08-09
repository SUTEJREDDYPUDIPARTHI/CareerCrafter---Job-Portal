package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.*;
import java.util.List;

public interface IResumeService {
    ResumeDTO uploadResume(ResumeDTO resumeDTO);
    ResumeDTO getResumeById(int id);
    List<ResumeDTO> getResumesByJobSeekerId(int jobSeekerId);
    ResumeDTO updateResume(ResumeDTO resumeDTO);
    void deleteResume(int id);
}
