package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.*;
import java.util.List;

public interface IEmployerService {
	
	EmployerDTO createEmployer(EmployerDTO employerDTO);
    EmployerDTO getEmployerById(int id);
    List<EmployerDTO> getAllEmployers();
    EmployerDTO updateEmployer(EmployerDTO employerDTO);
    void deleteEmployer(int id);

}
