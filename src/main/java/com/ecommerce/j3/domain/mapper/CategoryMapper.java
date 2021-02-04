package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.controller.dto.CategoryDto.CategoryApiRequest;
import com.ecommerce.j3.controller.dto.CategoryDto.CategoryApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CategoryMapper implements DefaultMapper<Category, CategoryApiRequest, CategoryApiResponse> {

    @Override
    public void updateFromDto(@MappingTarget Category entity, CategoryApiRequest dto){
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract Category toEntity(CategoryApiRequest dto);
    @Override
    public abstract CategoryApiResponse toDto(Category cart);
}
