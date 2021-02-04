package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Review;
import com.ecommerce.j3.controller.dto.ReviewDto.ReviewApiRequest;
import com.ecommerce.j3.controller.dto.ReviewDto.ReviewApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReviewMapper implements DefaultMapper<Review, ReviewApiRequest, ReviewApiResponse> {

    @Override
    public void updateFromDto(@MappingTarget Review entity, ReviewApiRequest dto){
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract Review toEntity(ReviewApiRequest dto);

    @Override
    public abstract ReviewApiResponse toDto(Review entity);
}
