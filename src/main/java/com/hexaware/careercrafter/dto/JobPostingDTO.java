package com.hexaware.careercrafter.dto;

import jakarta.validation.constraints.*;

public class JobPostingDTO {
	
	public enum JobType {
		FULL_TIME, PART_TIME, INTERNSHIP
	}

    private int jobPostingId;

    @NotNull(message="EmployerId is required")
    private int employerId;

    @NotBlank(message="Title is required")
    @Size(min=3, max = 100)
    private String title;

    @NotBlank(message="Description is required")
    @Size(min=5, max = 500)
    private String description;

    @NotBlank(message="Location is required")
    private String location;

    @PositiveOrZero(message="Salary is required")
    private double salary;

    @NotBlank(message="Job type is required")
    private JobType jobType;

    @NotBlank(message="Experience Level is required")
    private String experienceLevel;

    @NotBlank(message="Qualification is required")
    private String qualifications;
}
