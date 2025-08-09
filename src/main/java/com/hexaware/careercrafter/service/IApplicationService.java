package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.*;
import java.util.List;

public interface IApplicationService {
	
	ApplicationDTO applyForJob(ApplicationDTO applicationDTO);
    ApplicationDTO getApplicationById(int id);
    List<ApplicationDTO> getAllApplications();
    List<ApplicationDTO> getApplicationsByJobSeekerId(int seekerId);
    ApplicationDTO updateApplication(ApplicationDTO applicationDTO);
    void deleteApplication(int id);
}
