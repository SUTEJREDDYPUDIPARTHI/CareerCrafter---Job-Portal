package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.Employer;
import com.hexaware.careercrafter.exception.InvalidRequestException;
import com.hexaware.careercrafter.exception.ResourceNotFoundException;
import com.hexaware.careercrafter.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements IEmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public Employer createEmployer(Employer employer) {
        if (employer.getUser() == null || employer.getCompanyName() == null) {
            throw new InvalidRequestException("User and Company Name must be provided.");
        }
        return employerRepository.save(employer);
    }

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    @Override
    public Employer getEmployerById(int id) {
        return employerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found with ID: " + id));
    }

    @Override
    public void deleteEmployer(int id) {
        if (!employerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Employer with ID " + id + " does not exist.");
        }
        employerRepository.deleteById(id);
    }

    @Override
    public Employer updateEmployer(Employer employer) {
        if (!employerRepository.existsById(employer.getEmployerId())) {
            throw new ResourceNotFoundException("Cannot update. Employer with ID " + employer.getEmployerId() + " not found.");
        }
        return employerRepository.save(employer);
    }
}
