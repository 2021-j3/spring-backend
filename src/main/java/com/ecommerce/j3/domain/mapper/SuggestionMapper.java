package com.ecommerce.j3.domain.mapper;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.entity.Suggestion;
import com.ecommerce.j3.controller.dto.SuggestionDto.SuggestionApiRequest;
import com.ecommerce.j3.controller.dto.SuggestionDto.SuggestionApiResponse;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.ProductRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class SuggestionMapper implements DefaultMapper<Suggestion, SuggestionApiRequest, SuggestionApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Suggestion entity, SuggestionApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Suggestion entity, SuggestionApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "productId", target = "product")
    @Override
    public abstract Suggestion toEntity(SuggestionApiRequest dto);

    @Override
    public abstract SuggestionApiResponse toDto(Suggestion entity);
}
