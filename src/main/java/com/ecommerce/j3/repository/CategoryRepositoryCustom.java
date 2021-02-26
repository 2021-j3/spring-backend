package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepositoryCustom {
    @Transactional
    Long insert(Category entity);

    @Transactional
    void deleteByIdCustom(Long id);
}
