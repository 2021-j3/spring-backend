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

    @Mapping(source = "seller", target = "sellerId")
    @Override
    public abstract ProductApiResponse toApiResponse(Product entity);

    @Override
    public Product updateFromDto(@MappingTarget Product entity, ProductApiRequest dto){
        if (dto == null) return entity;
        // TODO: 구현 해야함, account mapper 참조

        Product req = toEntity(dto);
        return  Product.builder()
                .productId(entity.getProductId())
                .seller(entity.getSeller())
                .title(req.getTitle())
                .metaTitle(req.getMetaTitle())
                .slug(req.getSlug())
                .sku(req.getSku())
                .price(req.getPrice())
                .discountPrice(req.getDiscountPrice())
                .quantity(req.getQuantity())
                .thumbnailPath(req.getThumbnailPath())
                .imagePath(req.getImagePath())
                .content(req.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                // 최초에는 업데이트 가능, 이후 불가능
                .startsAt(entity.getStartsAt() == null ? req.getStartsAt() : entity.getStartsAt())
                .endsAt(entity.getEndsAt() == null ? req.getEndsAt() : entity.getEndsAt())
                .categories(req.getCategories())
                .tags(req.getTags())
                .build();
    }
}
