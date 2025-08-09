package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.SearchRecommendationDTO;
import com.hexaware.careercrafter.entities.SearchRecommendation;
import com.hexaware.careercrafter.entities.User;
import com.hexaware.careercrafter.exception.*;
import com.hexaware.careercrafter.repository.SearchRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchRecommendationServiceImpl implements ISearchRecommendationService {

    @Autowired
    private SearchRecommendationRepository searchRecommendationRepository;

    @Override
    public SearchRecommendationDTO createSearch(SearchRecommendationDTO dto) {
        if (dto.getSearchKeywords() == null || dto.getUserId() == 0) {
            throw new InvalidRequestException("Search keywords and userId are required.");
        }
        SearchRecommendation entity = mapToEntity(dto);
        SearchRecommendation saved = searchRecommendationRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public SearchRecommendationDTO getSearchById(int id) {
        SearchRecommendation entity = searchRecommendationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SearchRecommendation with ID " + id + " not found"));
        return mapToDTO(entity);
    }

    @Override
    public List<SearchRecommendationDTO> getSearchesByUserId(int userId) {
        List<SearchRecommendation> entities = searchRecommendationRepository.findByUserUserId(userId);
        List<SearchRecommendationDTO> dtos = new ArrayList<>();
        for (SearchRecommendation sr : entities) {
            dtos.add(mapToDTO(sr));
        }
        return dtos;
    }

    @Override
    public void deleteSearch(int id) {
        if (!searchRecommendationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. SearchRecommendation with ID " + id + " not found");
        }
        searchRecommendationRepository.deleteById(id);
    }

    @Override
    public SearchRecommendationDTO updateSearch(SearchRecommendationDTO dto) {
        if (!searchRecommendationRepository.existsById(dto.getSearchId())) {
            throw new ResourceNotFoundException("Cannot update. SearchRecommendation with ID " + dto.getSearchId() + " not found");
        }
        SearchRecommendation entity = mapToEntity(dto);
        SearchRecommendation saved = searchRecommendationRepository.save(entity);
        return mapToDTO(saved);
    }

    // --- Mapping methods ---

    private SearchRecommendationDTO mapToDTO(SearchRecommendation entity) {
        SearchRecommendationDTO dto = new SearchRecommendationDTO();
        dto.setSearchId(entity.getSearchId());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getUserId() : 0);
        dto.setSearchKeywords(entity.getSearchKeywords());
        dto.setSearchFilters(entity.getSearchFilters());
        return dto;
    }

    private SearchRecommendation mapToEntity(SearchRecommendationDTO dto) {
        SearchRecommendation entity = new SearchRecommendation();
        entity.setSearchId(dto.getSearchId());
        User user = new User();
        user.setUserId(dto.getUserId());
        entity.setUser(user);
        entity.setSearchKeywords(dto.getSearchKeywords());
        entity.setSearchFilters(dto.getSearchFilters());
        return entity;
    }
}
