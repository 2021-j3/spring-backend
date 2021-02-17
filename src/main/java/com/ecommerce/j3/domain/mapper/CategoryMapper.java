package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.controller.dto.CategoryDto.CategoryApiRequest;
import com.ecommerce.j3.controller.dto.CategoryDto.CategoryApiResponse;
import com.ecommerce.j3.domain.entity.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class CategoryMapper implements DefaultMapper<Category, CategoryApiRequest, CategoryApiResponse> {

    @Mapping(source = "parentId", target = "parent", nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Override
    public abstract Category toEntity(CategoryApiRequest dto);

    @Mapping(source = "parent", target = "parentId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Override
    public abstract CategoryApiResponse toApiResponse(Category cart);

    @Override
    public Category updateFromDto(@MappingTarget Category entity, CategoryApiRequest dto){
        if (dto == null) return entity;

        Category req = toEntity(dto);
        return Category.builder()
                .categoryId(entity.getCategoryId())
                .parent(req.getParent())
                .title(req.getTitle())
                .metaTitle(req.getMetaTitle())
                .slug(req.getSlug())
                .content(req.getContent())
                .build();
    }
}
