package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.SuggestionDto.SuggestionApiRequest;
import com.ecommerce.j3.controller.dto.SuggestionDto.SuggestionApiResponse;
import com.ecommerce.j3.domain.entity.Suggestion;
import com.ecommerce.j3.domain.entity.Suggestion.SuggestionBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-08T14:51:45+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class SuggestionMapperImpl extends SuggestionMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Suggestion toEntity(SuggestionApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        SuggestionBuilder suggestion = Suggestion.builder();

        suggestion.account( commonMapper.mapIdToAccount( dto.getAccountId() ) );
        suggestion.product( commonMapper.mapIdToProduct( dto.getProductId() ) );
        suggestion.suggestionId( dto.getSuggestionId() );
        suggestion.content( dto.getContent() );

        return suggestion.build();
    }

    @Override
    public SuggestionApiResponse toApiResponse(Suggestion entity) {
        if ( entity == null ) {
            return null;
        }

        SuggestionApiResponse suggestionApiResponse = new SuggestionApiResponse();

        suggestionApiResponse.setSuggestionId( entity.getSuggestionId() );
        suggestionApiResponse.setAccount( entity.getAccount() );
        suggestionApiResponse.setProduct( entity.getProduct() );
        suggestionApiResponse.setContent( entity.getContent() );

        return suggestionApiResponse;
    }
}
