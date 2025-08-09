package com.hexaware.careercrafter.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
	
	public enum ApplicationStatus {
		APPLIED, IN_REVIEW, SHORTLISTED, REJECTED, HIRED
    }

    private int applicationId;

    @NotNull(message="JobPostingID is required")
    private int jobPostingId;

    @NotNull(message="JobSeekerID is required")
    private int jobSeekerId;
    
    @NotNull(message="Status is required")
    private ApplicationStatus status;
    
    @Size(max = 2000, message = "Cover letter can't exceed 2000 characters")
    private String coverLetter;

    @Size(max = 500, message = "Resume file path can't exceed 500 characters")
    private String resumeFilePath;
}
