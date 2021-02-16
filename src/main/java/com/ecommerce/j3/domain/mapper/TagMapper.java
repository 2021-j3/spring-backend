package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.TagDto.TagApiRequest;
import com.ecommerce.j3.controller.dto.TagDto.TagApiResponse;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.Tag;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class TagMapper implements DefaultMapper<Tag, TagApiRequest, TagApiResponse> {

    @Override
    public abstract Tag toEntity(TagApiRequest dto);

    @Override
    public abstract TagApiResponse toApiResponse(Tag entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Tag updateFromDto(@MappingTarget Tag entity, TagApiRequest dto){
        if(dto == null) return entity;
        return Tag.builder()
                .tagId(entity.getTagId())
                .title(dto.getTitle())
                .metaTitle(dto.getMetaTitle())
                .slug(dto.getSlug())
                .build();
    }
}
