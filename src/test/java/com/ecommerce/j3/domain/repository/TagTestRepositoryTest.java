package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.Tag;
import com.ecommerce.j3.domain.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TagTestRepositoryTest extends J3ApplicationTests {
    @Autowired
    private TagRepository tagRepository;

    @Test
    @Transactional
    public void create(){
        Tag tag = new Tag();

        tag.setTitle("tag Title");
        tag.setMetaTitle("tag metaTitle");
        tag.setContent("tag Content");
        tag.setSlug("/tag_Slug");

        Tag newTag = tagRepository.save(tag);

    }
}