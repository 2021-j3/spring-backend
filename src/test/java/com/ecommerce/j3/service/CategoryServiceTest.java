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
        Category categoryFromDB = categoryRepository.getOne(category.getCategoryId());
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
        Category categoryFromDB = categoryRepository.getOne(category.getCategoryId());
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

        Category categoryFromDB = categoryService.findOne(category.getCategoryId()).get();
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
        category2.setTitle("태블릿");
        category2.setSlug("fasdf");
        category2.setContent("content");
        category2.setParent(category1);
        categoryService.save(category2);

        Category category3 = new Category();
        category3.setTitle("아이패드");
        category3.setSlug("fasdf");
        category3.setContent("content");
        category3.setParent(category2);
        categoryService.save(category3);

        Category categoryFromDB = categoryService.findOne(category3.getCategoryId()).get();
        Assertions
                .assertThat(category2.getTitle())
                .isEqualTo(categoryFromDB.getParent().getTitle());

        Category category4 = new Category();
        category4.setTitle("갤탭");
        category4.setSlug("fasdf");
        category4.setContent("content");
        category4.setParent(category2);
        categoryService.save(category4);


        Category categoryFromDB2 = categoryService.findOne(category4.getCategoryId()).get();

        System.out.println(categoryFromDB.getTitle());
        System.out.println(categoryFromDB2.getTitle());

        System.out.println(categoryFromDB.getParent().getTitle());
        System.out.println(categoryFromDB2.getParent().getTitle());

    }
}