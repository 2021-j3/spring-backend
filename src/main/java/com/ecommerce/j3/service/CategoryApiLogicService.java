package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.CategoryDto.CategoryApiRequest;
import com.ecommerce.j3.controller.dto.CategoryDto.CategoryApiResponse;
import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.mapper.CategoryMapper;
import com.ecommerce.j3.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryApiLogicService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryApiResponse saveCategory(CategoryApiRequest request) {
        Category category = categoryMapper.toEntity(request);
        categoryRepository.save(category);
        return categoryMapper.toApiResponse(category);
    }

    public CategoryApiResponse updateCategory(CategoryApiRequest request) {
        Category categoryFromDB = findById(request.getCategoryId());
        categoryMapper.updateFromDto(categoryFromDB, request);
        categoryRepository.save(categoryFromDB);
        return categoryMapper.toApiResponse(categoryFromDB);
    }

    public CategoryApiResponse findCategory(Long categoryId) {
        Category categoryFromDB = findById(categoryId);
        return categoryMapper.toApiResponse(categoryFromDB);
    }

    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // 패키지 한정자, service 패키지 내에서만 접근 가능
    Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    List<Category> findByIds(List<Long> ids){
        return  ids == null ? null : categoryRepository.findByCategoryIdIn(ids);
    }
}
