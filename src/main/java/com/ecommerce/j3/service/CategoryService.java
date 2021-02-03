package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.CategoryDto;
import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.mapper.CategoryMapper;
import com.ecommerce.j3.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDto.CategoryApiResponse save(CategoryDto.CategoryApiRequest request){
        Category category = categoryMapper.toEntity(request);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    public Category save(Category category){
        categoryRepository.save(category);
        return category;
    }

    public Category update(Category category){
        categoryRepository.save(category);
        return category;
    }

    public Optional<Category> findOne(Long categoryId){
        return categoryRepository.findById(categoryId);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void remove(Category category){
        categoryRepository.delete(category);
    }
}
