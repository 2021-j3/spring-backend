package com.ecommerce.j3.domain.mapper;


import com.ecommerce.j3.domain.entity.*;
import com.ecommerce.j3.controller.dto.ReviewDto.ReviewApiRequest;
import com.ecommerce.j3.controller.dto.ReviewDto.ReviewApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class ReviewMapper implements DefaultMapper<Review, ReviewApiRequest, ReviewApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Review entity, ReviewApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Review entity, ReviewApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Mapping(source = "parentId", target = "parent")
    @Mapping(source = "orderItemId", target = "orderItem")
    @Mapping(source = "accountId", target = "account")
    @Override
    public abstract Review toEntity(ReviewApiRequest dto);

    @Override
    public abstract ReviewApiResponse toDto(Review entity);
}
