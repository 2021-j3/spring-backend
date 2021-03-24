package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.Suggestion;
import com.ecommerce.j3.controller.dto.SuggestionDto.SuggestionApiRequest;
import com.ecommerce.j3.controller.dto.SuggestionDto.SuggestionApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class SuggestionMapper implements DefaultMapper<Suggestion, SuggestionApiRequest, SuggestionApiResponse> {

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "productId", target = "product")
    @Override
    public abstract Suggestion toEntity(SuggestionApiRequest dto);

    @Override
    public abstract SuggestionApiResponse toApiResponse(Suggestion entity);

    @Override
    public Suggestion updateFromDto(@MappingTarget Suggestion entity, SuggestionApiRequest dto){
        if (dto == null) return entity;
        Suggestion req = toEntity(dto);
        return  Suggestion.builder()
                .suggestionId(entity.getSuggestionId())
                .account(entity.getAccount())
                .product(entity.getProduct())
                .content(entity.getContent())
                .build();
    }
}
