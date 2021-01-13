package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Category;
import com.ecommerce.j3.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class CategoryServiceTest {
    @Autowired CategoryService categoryService;
    @Autowired CategoryRepository categoryRepository;


    @Test
    void save() {
        // given
        Category category = new Category();
        category.setTitle("title");
        category.setSlug("fasdf");
        category.setContent("content");
        // when
        categoryService.save(category);

        //then
        Category categoryFromDB = categoryRepository.getOne(category.getId());
        Assertions
                .assertThat(category.getTitle())
                .isEqualTo(categoryFromDB.getTitle());
    }

    @Test
    void update() {
        // given
        Category category = new Category();
        category.setTitle("title");
        category.setSlug("fasdf");
        category.setContent("content");
        categoryService.save(category);

        // when
        String new_title = "new title";
        category.setTitle(new_title);
        categoryService.update(category);

        // then
        Category categoryFromDB = categoryRepository.getOne(category.getId());
        Assertions
                .assertThat(new_title)
                .isEqualTo(categoryFromDB.getTitle());
    }

    @Test
    void findOneById() {
        // given
        Category category = new Category();
        category.setTitle("title");
        category.setSlug("fasdf");
        category.setContent("content");
        categoryService.save(category);

        Category categoryFromDB = categoryService.findOne(category.getId()).get();
        Assertions
                .assertThat(category.getTitle())
                .isEqualTo(categoryFromDB.getTitle());
    }


    @Test
    void remove() {
        // given
        Category category = new Category();
        category.setTitle("title");
        category.setSlug("fasdf");
        category.setContent("content");
        categoryService.save(category);
        // when
        int cnt_that = categoryService.findAll().size();
        categoryService.remove(category);

        //then
        int cnt_now = categoryService.findAll().size();
        Assertions
                .assertThat(cnt_that - 1)
                .isEqualTo(cnt_now);
    }

    @Test
    void Parent(){
        // given
        Category category1 = new Category();
        category1.setTitle("title1");
        category1.setSlug("fasdf");
        category1.setContent("content");
        categoryService.save(category1);

        Category category2 = new Category();
        category2.setTitle("title2");
        category2.setSlug("fasdf");
        category2.setContent("content");
        category2.setParent(category1);
        categoryService.save(category2);

        Category category3 = new Category();
        category3.setTitle("title3");
        category3.setSlug("fasdf");
        category3.setContent("content");
        category3.setParent(category2);
        categoryService.save(category3);

        Category categoryFromDB = categoryService.findOne(category3.getId()).get();
        Assertions
                .assertThat(category2.getTitle())
                .isEqualTo(categoryFromDB.getParent().getTitle());
    }
}