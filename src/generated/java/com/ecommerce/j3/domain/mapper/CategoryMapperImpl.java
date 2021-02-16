package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.CategoryDto.CategoryApiRequest;
import com.ecommerce.j3.controller.dto.CategoryDto.CategoryApiResponse;
import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.entity.Category.CategoryBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-16T23:36:09+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl extends CategoryMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Category toEntity(CategoryApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryBuilder category = Category.builder();

        category.parent( commonMapper.mapIdToCategory( dto.getCategoryId() ) );
        category.categoryId( dto.getCategoryId() );
        category.title( dto.getTitle() );
        category.metaTitle( dto.getMetaTitle() );
        category.slug( dto.getSlug() );
        category.content( dto.getContent() );

        return category.build();
    }

    @Override
    public CategoryApiResponse toApiResponse(Category cart) {
        if ( cart == null ) {
            return null;
        }

        CategoryApiResponse categoryApiResponse = new CategoryApiResponse();

        categoryApiResponse.setCategoryId( cart.getCategoryId() );
        categoryApiResponse.setParent( cart.getParent() );
        categoryApiResponse.setTitle( cart.getTitle() );
        categoryApiResponse.setMetaTitle( cart.getMetaTitle() );
        categoryApiResponse.setSlug( cart.getSlug() );
        categoryApiResponse.setContent( cart.getContent() );

        return categoryApiResponse;
    }
}
