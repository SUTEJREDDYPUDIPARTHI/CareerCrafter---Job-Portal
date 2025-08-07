package com.hexaware.careercrafter.dto;

import jakarta.validation.constraints.*;

public class ApplicationDTO {
	
	public enum ApplicationStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    private int applicationId;

    @NotNull(message="JobPostingID is required")
    private int jobPostingId;

    @NotNull(message="JobSeekerID is required")
    private int jobSeekerId;

    @NotNull(message="Status is required")
    private ApplicationStatus status; // Consider enum if fixed statuses like "pending", "accepted", etc.
}
