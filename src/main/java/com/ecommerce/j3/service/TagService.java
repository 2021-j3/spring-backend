package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.TagDto;
import com.ecommerce.j3.domain.entity.Tag;
import com.ecommerce.j3.domain.entity.Tag;
import com.ecommerce.j3.domain.mapper.TagMapper;
import com.ecommerce.j3.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagDto.TagApiResponse save(TagDto.TagApiRequest request){
        Tag tag = tagMapper.toEntity(request);
        tagRepository.save(tag);
        return tagMapper.toDto(tag);
    }
    public Tag save(Tag tag){
        tagRepository.save(tag);
        return tag;
    }

    public Tag update(Tag tag){
        tagRepository.save(tag);
        return tag;
    }

    public Optional<Tag> findOne(Long tagId){
        return tagRepository.findById(tagId);
    }

    public List<Tag> findAll(){
        return tagRepository.findAll();
    }

    public void remove(Tag tag){
        tagRepository.delete(tag);
    }
}
