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
public class EmployerDTO {

    private int employerId;

    @NotNull(message="UserID is required")
    private int userId;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Company description is required")
    private String companyDescription;
    
}
