package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.Tag;
import com.ecommerce.j3.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository){this.tagRepository = tagRepository;}

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
