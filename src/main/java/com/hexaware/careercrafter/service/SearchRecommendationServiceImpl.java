package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.entities.SearchRecommendation;
import com.hexaware.careercrafter.repository.SearchRecommendationRepository;
import com.hexaware.careercrafter.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchRecommendationServiceImpl implements ISearchRecommendationService {

    @Autowired
    private SearchRecommendationRepository searchRecommendationRepository;

    @Override
    public SearchRecommendation createSearch(SearchRecommendation search) {
        if (search.getSearchKeywords() == null || search.getUser() == null) {
            throw new InvalidRequestException("Search keywords and associated user are required.");
        }
        return searchRecommendationRepository.save(search);
    }

    @Override
    public SearchRecommendation getSearchById(int id) {
        return searchRecommendationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SearchRecommendation with ID " + id + " not found"));
    }

    @Override
    public List<SearchRecommendation> getSearchesByUserId(int userId) {
        return searchRecommendationRepository.findByUserUserId(userId);
    }

    @Override
    public void deleteSearch(int id) {
        if (!searchRecommendationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. SearchRecommendation with ID " + id + " not found");
        }
        searchRecommendationRepository.deleteById(id);
    }

    @Override
    public SearchRecommendation updateSearch(SearchRecommendation search) {
        if (!searchRecommendationRepository.existsById(search.getSearchId())) {
            throw new ResourceNotFoundException("Cannot update. SearchRecommendation with ID " + search.getSearchId() + " not found");
        }
        return searchRecommendationRepository.save(search);
    }
}
