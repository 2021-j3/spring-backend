package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.network.request.ProductApiRequest;
import com.ecommerce.j3.domain.network.response.ProductApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductMapper implements DefaultMapper<Product, ProductApiRequest, ProductApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Product entity, ProductApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Product entity, ProductApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract Product toEntity(ProductApiRequest dto);

    @Override
    public abstract ProductApiResponse toDto(Product entity);
}
