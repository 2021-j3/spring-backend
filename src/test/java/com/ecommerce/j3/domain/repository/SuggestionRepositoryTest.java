package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.Suggestion;
import com.ecommerce.j3.domain.repository.AccountRepository;
import com.ecommerce.j3.domain.repository.ProductRepository;
import com.ecommerce.j3.domain.repository.SuggestionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SuggestionRepositoryTest extends J3ApplicationTests {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Transactional
    @Test
    public void create(){
        Suggestion suggestion = new Suggestion();

        suggestion.setAccount(accountRepository.getOne(2L));
        suggestion.setProduct(productRepository.getOne(2L));
        suggestion.setContent("Suggestion content");

        Suggestion newSuggestion = suggestionRepository.save(suggestion);
        Assert.assertNotNull(newSuggestion);

    }

}
