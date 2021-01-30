package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Suggestion;
import com.ecommerce.j3.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService {
    private SuggestionRepository suggestionRepository;

    @Autowired
    public SuggestionService(SuggestionRepository suggestionRepository){this.suggestionRepository = suggestionRepository;}

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
