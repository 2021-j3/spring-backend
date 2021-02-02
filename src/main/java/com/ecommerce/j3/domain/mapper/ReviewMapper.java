package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Review;
import com.ecommerce.j3.domain.network.ReviewDto.ReviewApiRequest;
import com.ecommerce.j3.domain.network.ReviewDto.ReviewApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReviewMapper implements DefaultMapper<Review, ReviewApiRequest, ReviewApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Review entity, ReviewApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Review entity, ReviewApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract Review toEntity(ReviewApiRequest dto);

    @Override
    public abstract ReviewApiResponse toDto(Review entity);
}
