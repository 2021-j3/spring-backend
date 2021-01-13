package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Tag;
import com.ecommerce.j3.repository.TagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class TagServiceTest {
    @Autowired TagService tagService;
    @Autowired TagRepository tagRepository;


    @Test
    void save() {
        // given
        Tag tag = new Tag();
        tag.setTitle("title");
        tag.setSlug("fasdf");
        tag.setContent("content");
        // when
        tagService.save(tag);

        //then
        Tag tagFromDB = tagRepository.getOne(tag.getTagId());
        Assertions
                .assertThat(tag.getTitle())
                .isEqualTo(tagFromDB.getTitle());
    }

    @Test
    void update() {
        // given
        Tag tag = new Tag();
        tag.setTitle("title");
        tag.setSlug("fasdf");
        tag.setContent("content");
        tagService.save(tag);

        // when
        String new_title = "new title";
        tag.setTitle(new_title);
        tagService.update(tag);

        // then
        Tag tagFromDB = tagRepository.getOne(tag.getTagId());
        Assertions
                .assertThat(new_title)
                .isEqualTo(tagFromDB.getTitle());
    }

    @Test
    void findOneById() {
        // given
        Tag tag = new Tag();
        tag.setTitle("title");
        tag.setSlug("fasdf");
        tag.setContent("content");
        tagService.save(tag);

        Tag tagFromDB = tagService.findOne(tag.getTagId()).get();
        Assertions
                .assertThat(tag.getTitle())
                .isEqualTo(tagFromDB.getTitle());
    }


    @Test
    void remove() {
        // given
        Tag tag = new Tag();
        tag.setTitle("title");
        tag.setSlug("fasdf");
        tag.setContent("content");
        tagService.save(tag);
        // when
        int cnt_that = tagService.findAll().size();
        tagService.remove(tag);

        //then
        int cnt_now = tagService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }
}