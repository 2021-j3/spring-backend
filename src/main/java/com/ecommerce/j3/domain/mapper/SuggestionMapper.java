package com.ecommerce.j3.domain.mapper;

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
    public abstract SuggestionApiResponse toDto(Suggestion entity);

    @Override
    public void updateFromDto(@MappingTarget Suggestion entity, SuggestionApiRequest dto){
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
    }
}
