package com.hexaware.careercrafter.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRecommendationDTO {

    private int searchId;

    @NotNull(message="UserId is required")
    private int userId;

    @NotBlank(message = "Search keywords required")
    private String searchKeywords;

    @Size(max = 500)
    private String searchFilters;
}
