package com.hexaware.careercrafter.dto;

import jakarta.validation.constraints.*;

public class EmployerDTO {

    private int employerId;

    @NotNull(message="UserID is required")
    private int userId;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Company address is required")
    private String companyAddress;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
    private String phone;

    @Email(message="Email is required")
    private String email;
}
