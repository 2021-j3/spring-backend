package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.SuggestionDto.SuggestionApiRequest;
import com.ecommerce.j3.controller.dto.SuggestionDto.SuggestionApiResponse;
import com.ecommerce.j3.domain.entity.Suggestion;
import com.ecommerce.j3.domain.mapper.SuggestionMapper;
import com.ecommerce.j3.repository.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SuggestionApiLogicService {
    private final SuggestionRepository suggestionRepository;
    private final SuggestionMapper suggestionMapper;

    public SuggestionApiResponse saveSuggestion(SuggestionApiRequest request) {
        Suggestion suggestion = suggestionMapper.toEntity(request);
        suggestionRepository.save(suggestion);
        return suggestionMapper.toApiResponse(suggestion);
    }

    public SuggestionApiResponse updateSuggestion(SuggestionApiRequest request) {
        Suggestion suggestionFromDB = findById(request.getSuggestionId());
        suggestionMapper.updateFromDto(suggestionFromDB, request);
        suggestionRepository.save(suggestionFromDB);
        return suggestionMapper.toApiResponse(suggestionFromDB);
    }

    public SuggestionApiResponse findSuggestion(Long suggestionId) {
        Suggestion suggestionFromDB = findById(suggestionId);
        return suggestionMapper.toApiResponse(suggestionFromDB);
    }

    public void removeSuggestion(Long id) {
        suggestionRepository.deleteById(id);
    }

    // 패키지 한정자, service패키지 내에서만 접근 가능
    Suggestion findById(Long id) {
        return suggestionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
