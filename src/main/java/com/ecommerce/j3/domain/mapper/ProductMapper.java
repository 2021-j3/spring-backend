package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiRequest;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class ProductMapper implements DefaultMapper<Product, ProductApiRequest, ProductApiResponse> {

    @Mapping(source = "sellerId", target = "seller")
    @Mapping(source = "categoryIds", target = "categories")
    @Mapping(source = "tagIds", target = "tags")
    @Override
    public abstract Product toEntity(ProductApiRequest dto);

    @Override
    public abstract ProductApiResponse toApiResponseDto(Product entity);

    @Override
    public void updateFromDto(@MappingTarget Product entity, ProductApiRequest dto){
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
    }
}
