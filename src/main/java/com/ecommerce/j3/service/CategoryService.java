package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){this.categoryRepository = categoryRepository;}

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
