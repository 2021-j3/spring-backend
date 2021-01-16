package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.repository.CategoryRepository;
import com.ecommerce.j3.domain.repository.AccountRepository;
import com.ecommerce.j3.domain.repository.ProductRepository;
import com.ecommerce.j3.domain.repository.ReviewRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryRepositoryTest extends J3ApplicationTests {
    @Autowired
    private CategoryRepository categoryRepository;

//    @Transactional
    @Test
    public void create(){
        Category category = new Category();
//        category.setParent(category.getParent());
        category.setTitle("Category Title");
        category.setMetaTitle("Category Meta Title");
        category.setSlug("/slug");
        category.setContent("category Content");

        Category newCategory = categoryRepository.save(category);
        Assert.assertNotNull(newCategory);
    }

}

