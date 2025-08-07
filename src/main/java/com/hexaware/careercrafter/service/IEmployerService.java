package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.*;
import java.util.List;

public interface IEmployerService {
	
	Employer createEmployer(Employer employer);
    Employer getEmployerById(int id);
    List<Employer> getAllEmployers();
    Employer updateEmployer(Employer employer);
    void deleteEmployer(int id);

}
