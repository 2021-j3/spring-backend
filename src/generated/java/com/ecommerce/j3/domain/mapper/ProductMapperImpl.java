package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.ProductDto.ProductApiRequest;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiResponse;
import com.ecommerce.j3.controller.dto.ProductDto.ProductApiResponse.ProductApiResponseBuilder;
import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.entity.Product.ProductBuilder;
import com.ecommerce.j3.domain.entity.Tag;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-16T23:36:09+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl extends ProductMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Product toEntity(ProductApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        ProductBuilder product = Product.builder();

        product.seller( commonMapper.mapIdToAccount( dto.getSellerId() ) );
        product.categories( commonMapper.mapIdsToCategorySet( dto.getCategoryIds() ) );
        product.tags( commonMapper.mapIdsToTagSet( dto.getTagIds() ) );
        product.productId( dto.getProductId() );
        product.title( dto.getTitle() );
        product.metaTitle( dto.getMetaTitle() );
        product.slug( dto.getSlug() );
        product.sku( dto.getSku() );
        product.price( dto.getPrice() );
        product.discountRate( dto.getDiscountRate() );
        product.quantity( dto.getQuantity() );
        product.thumbnailPath( dto.getThumbnailPath() );
        product.imagePath( dto.getImagePath() );
        product.content( dto.getContent() );
        product.createdAt( dto.getCreatedAt() );
        product.updatedAt( dto.getUpdatedAt() );
        product.startsAt( dto.getStartsAt() );
        product.endsAt( dto.getEndsAt() );

        return product.build();
    }

    @Override
    public ProductApiResponse toApiResponse(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductApiResponseBuilder productApiResponse = ProductApiResponse.builder();

        productApiResponse.productId( entity.getProductId() );
        productApiResponse.seller( entity.getSeller() );
        productApiResponse.title( entity.getTitle() );
        productApiResponse.metaTitle( entity.getMetaTitle() );
        productApiResponse.slug( entity.getSlug() );
        productApiResponse.sku( entity.getSku() );
        productApiResponse.price( entity.getPrice() );
        productApiResponse.discountRate( entity.getDiscountRate() );
        productApiResponse.quantity( entity.getQuantity() );
        productApiResponse.thumbnailPath( entity.getThumbnailPath() );
        productApiResponse.imagePath( entity.getImagePath() );
        productApiResponse.content( entity.getContent() );
        productApiResponse.createdAt( entity.getCreatedAt() );
        productApiResponse.updatedAt( entity.getUpdatedAt() );
        productApiResponse.startsAt( entity.getStartsAt() );
        productApiResponse.endsAt( entity.getEndsAt() );
        Set<Category> set = entity.getCategories();
        if ( set != null ) {
            productApiResponse.categories( new HashSet<Category>( set ) );
        }
        Set<Tag> set1 = entity.getTags();
        if ( set1 != null ) {
            productApiResponse.tags( new HashSet<Tag>( set1 ) );
        }

        return productApiResponse.build();
    }
}