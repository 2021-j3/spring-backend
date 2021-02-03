package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.SuggestionDto;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Suggestion;
import com.ecommerce.j3.domain.entity.Suggestion;
import com.ecommerce.j3.domain.mapper.SuggestionMapper;
import com.ecommerce.j3.repository.SuggestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SuggestionService {
    private SuggestionRepository suggestionRepository;
    private final SuggestionMapper suggestionMapper;

    public SuggestionDto.SuggestionApiResponse save(SuggestionDto.SuggestionApiRequest request){
        Suggestion suggestion = suggestionMapper.toEntity(request);
        suggestionRepository.save(suggestion);
        return suggestionMapper.toDto(suggestion);
    }
    public Suggestion save(Suggestion suggestion){
        suggestionRepository.save(suggestion);
        return suggestion;
    }

    public Suggestion update(Suggestion suggestion){
        suggestionRepository.save(suggestion);
        return suggestion;
    }


    public Optional<Suggestion> findOne(Long suggestionId){
        return suggestionRepository.findById(suggestionId);
    }

    public List<Suggestion> findAll(){
        return suggestionRepository.findAll();
    }

    public void remove(Suggestion suggestion){
        suggestionRepository.delete(suggestion);
    }
}
