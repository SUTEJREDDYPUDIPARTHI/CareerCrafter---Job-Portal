package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.*;
import java.util.List;

public interface IApplicationService {
	
	Application applyForJob(Application application);
    Application getApplicationById(int id);
    List<Application> getAllApplications();
    List<Application> getApplicationsByJobSeekerId(int seekerId);
    Application updateApplication(Application application);
    void deleteApplication(int id);

}
