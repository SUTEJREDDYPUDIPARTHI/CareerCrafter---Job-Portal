package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.EmployerDTO;
import com.hexaware.careercrafter.entities.Employer;
import com.hexaware.careercrafter.entities.User;
import com.hexaware.careercrafter.exception.InvalidRequestException;
import com.hexaware.careercrafter.exception.ResourceNotFoundException;
import com.hexaware.careercrafter.repository.EmployerRepository;
import com.hexaware.careercrafter.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerServiceImpl implements IEmployerService {

    @Autowired
    private EmployerRepository employerRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public EmployerDTO createEmployer(EmployerDTO employerDTO) {
        if (employerDTO.getUserId() == 0 || employerDTO.getCompanyName() == null) {
            throw new InvalidRequestException("UserId and Company Name must be provided.");
        }

        User user = userRepository.findById(employerDTO.getUserId())
                .orElseThrow(() -> new InvalidRequestException("Invalid UserId: " + employerDTO.getUserId()));

        Employer employer = dtoToEntity(employerDTO);
        employer.setUser(user);
        Employer saved = employerRepository.save(employer);
        return entityToDto(saved);
    }

    @Override
    public List<EmployerDTO> getAllEmployers() {
        List<Employer> employers = employerRepository.findAll();
        List<EmployerDTO> dtoList = new ArrayList<>();
        for (Employer employer : employers) {
            dtoList.add(entityToDto(employer));
        }
        return dtoList;
    }

    @Override
    public EmployerDTO getEmployerById(int id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found with ID: " + id));
        return entityToDto(employer);
    }

    @Override
    public void deleteEmployer(int id) {
        if (!employerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Employer with ID " + id + " does not exist.");
        }
        employerRepository.deleteById(id);
    }

    @Override
    public EmployerDTO updateEmployer(EmployerDTO employerDTO) {
        if (!employerRepository.existsById(employerDTO.getEmployerId())) {
            throw new ResourceNotFoundException("Cannot update. Employer with ID " + employerDTO.getEmployerId() + " not found.");
        }
        User user = userRepository.findById(employerDTO.getUserId())
                .orElseThrow(() -> new InvalidRequestException("Invalid UserId: " + employerDTO.getUserId()));

        Employer employer = dtoToEntity(employerDTO);
        employer.setUser(user);
        Employer updated = employerRepository.save(employer);
        return entityToDto(updated);
    }

    private EmployerDTO entityToDto(Employer employer) {
        EmployerDTO dto = new EmployerDTO();
        dto.setEmployerId(employer.getEmployerId());
        dto.setUserId(employer.getUser().getUserId());
        dto.setCompanyName(employer.getCompanyName());
        dto.setCompanyDescription(employer.getCompanyDescription());
        return dto;
    }

    private Employer dtoToEntity(EmployerDTO dto) {
        Employer employer = new Employer();
        employer.setEmployerId(dto.getEmployerId());
        employer.setCompanyName(dto.getCompanyName());
        employer.setCompanyDescription(dto.getCompanyDescription());
        return employer;
    }
}
