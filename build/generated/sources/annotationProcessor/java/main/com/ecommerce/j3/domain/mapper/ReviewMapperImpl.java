package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.ReviewDto.ReviewApiRequest;
import com.ecommerce.j3.controller.dto.ReviewDto.ReviewApiResponse;
import com.ecommerce.j3.domain.entity.Review;
import com.ecommerce.j3.domain.entity.Review.ReviewBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-07T15:35:00+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl extends ReviewMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Review toEntity(ReviewApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        ReviewBuilder review = Review.builder();

        review.parent( commonMapper.mapIdToReview( dto.getParentId() ) );
        review.orderItem( commonMapper.mapIdToOrderItem( dto.getOrderItemId() ) );
        review.account( commonMapper.mapIdToAccount( dto.getAccountId() ) );
        review.reviewId( dto.getReviewId() );
        review.rate( dto.getRate() );
        review.title( dto.getTitle() );
        review.content( dto.getContent() );
        review.createdAt( dto.getCreatedAt() );

        return review.build();
    }

    @Override
    public ReviewApiResponse toApiResponse(Review entity) {
        if ( entity == null ) {
            return null;
        }

        ReviewApiResponse reviewApiResponse = new ReviewApiResponse();

        reviewApiResponse.setReviewId( entity.getReviewId() );
        reviewApiResponse.setParent( entity.getParent() );
        reviewApiResponse.setOrderItem( entity.getOrderItem() );
        reviewApiResponse.setAccount( entity.getAccount() );
        reviewApiResponse.setRate( entity.getRate() );
        reviewApiResponse.setTitle( entity.getTitle() );
        reviewApiResponse.setContent( entity.getContent() );
        reviewApiResponse.setCreatedAt( entity.getCreatedAt() );

        return reviewApiResponse;
    }
}
