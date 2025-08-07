package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.entities.SearchRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SearchRecommendationRepository extends JpaRepository<SearchRecommendation, Integer> {
    List<SearchRecommendation> findByUserUserId(int userId);

}
