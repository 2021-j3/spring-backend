package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.CategoryDto;
import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.mapper.CategoryMapper;
import com.ecommerce.j3.repository.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
class CategoryApiLogicServiceTest {
    @Autowired CategoryRepository categoryRepository;
    @Autowired CategoryMapper categoryMapper;

    @Test
    @Rollback(value = false)
    @Transactional
    void testInsertCategory(){
        Category category = categoryMapper.toEntity(CategoryDto.CategoryApiRequest.builder()
                .title("33242")
                .parentId(2L)
                .metaTitle("423432")
                .slug("3243")
                .build());
            categoryRepository.insert(category);
//        System.out.println("RESULT " +result);
//        assertThat(result).isNotNull();
    }
}