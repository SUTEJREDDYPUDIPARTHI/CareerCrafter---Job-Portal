package com.hexaware.careercrafter.dto;

import com.hexaware.careercrafter.entities.JobPosting.JobType;
import jakarta.validation.constraints.*;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingDTO {

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

    @NotNull(message="Job type is required")
    private JobType jobType;

    private boolean active;

}
