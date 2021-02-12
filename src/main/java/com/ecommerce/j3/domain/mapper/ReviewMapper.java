package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.Review;
import com.ecommerce.j3.controller.dto.ReviewDto.ReviewApiRequest;
import com.ecommerce.j3.controller.dto.ReviewDto.ReviewApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class ReviewMapper implements DefaultMapper<Review, ReviewApiRequest, ReviewApiResponse> {

    @Mapping(source = "parentId", target = "parent")
    @Mapping(source = "orderItemId", target = "orderItem")
    @Mapping(source = "accountId", target = "account")
    @Override
    public abstract Review toEntity(ReviewApiRequest dto);

    @Override
    public abstract ReviewApiResponse toApiResponseDto(Review entity);

    @Override
    public void updateFromDto(@MappingTarget Review entity, ReviewApiRequest dto){
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
    }
}
