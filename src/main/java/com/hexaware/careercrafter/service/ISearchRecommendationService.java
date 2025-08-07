package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.*;
import java.util.List;

public interface ISearchRecommendationService {
    SearchRecommendation createSearch(SearchRecommendation search);
    SearchRecommendation getSearchById(int id);
    List<SearchRecommendation> getSearchesByUserId(int userId);
    SearchRecommendation updateSearch(SearchRecommendation searchRecommendation);
    void deleteSearch(int id);
}
