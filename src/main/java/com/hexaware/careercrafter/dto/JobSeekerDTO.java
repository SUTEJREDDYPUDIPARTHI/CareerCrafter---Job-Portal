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
public class JobSeekerDTO {

    private int jobSeekerId;

    @NotNull(message = "User ID is required")
    private int userId;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must be 10 digits and start with 6-9")
    private String phone;

    @Size(max = 150, message = "Address can't exceed 150 characters")
    private String address;

    @Size(max = 100, message = "Education can't exceed 100 characters")
    private String education;

    @Size(max = 100, message = "Skills can't exceed 100 characters")
    private String skills;

    @Min(value=0, message="Experience must be 0 or more")
    @Max(value=50, message="Experience must be 50 or more")
    private Integer experience;
}
