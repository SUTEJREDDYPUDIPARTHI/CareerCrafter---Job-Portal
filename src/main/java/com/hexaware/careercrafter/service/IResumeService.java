package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.*;
import java.util.List;

public interface IResumeService {
    Resume uploadResume(Resume resume);
    Resume getResumeById(int id);
    List<Resume> getResumesByJobSeekerId(int jobSeekerId);
    Resume updateResume(Resume resume);
    void deleteResume(int id);
}
