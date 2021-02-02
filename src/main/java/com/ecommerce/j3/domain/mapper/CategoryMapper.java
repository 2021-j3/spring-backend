package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.network.CategoryDto.CategoryApiRequest;
import com.ecommerce.j3.domain.network.CategoryDto.CategoryApiResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public abstract class CategoryMapper implements DefaultMapper<Category, CategoryApiRequest, CategoryApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Category entity, CategoryApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Category entity, CategoryApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract Category toEntity(CategoryApiRequest dto);
    @Override
    public abstract CategoryApiResponse toDto(Category cart);
}
