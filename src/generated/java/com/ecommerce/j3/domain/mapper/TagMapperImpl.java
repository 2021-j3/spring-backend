package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.TagDto.TagApiRequest;
import com.ecommerce.j3.controller.dto.TagDto.TagApiResponse;
import com.ecommerce.j3.domain.entity.Tag;
import com.ecommerce.j3.domain.entity.Tag.TagBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-16T23:36:09+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class TagMapperImpl extends TagMapper {

    @Override
    public Tag toEntity(TagApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        TagBuilder tag = Tag.builder();

        tag.tagId( dto.getTagId() );
        tag.title( dto.getTitle() );
        tag.metaTitle( dto.getMetaTitle() );
        tag.slug( dto.getSlug() );
        tag.content( dto.getContent() );

        return tag.build();
    }

    @Override
    public TagApiResponse toApiResponse(Tag entity) {
        if ( entity == null ) {
            return null;
        }

        TagApiResponse tagApiResponse = new TagApiResponse();

        tagApiResponse.setTagId( entity.getTagId() );
        tagApiResponse.setTitle( entity.getTitle() );
        tagApiResponse.setMetaTitle( entity.getMetaTitle() );
        tagApiResponse.setSlug( entity.getSlug() );
        tagApiResponse.setContent( entity.getContent() );

        return tagApiResponse;
    }
}
