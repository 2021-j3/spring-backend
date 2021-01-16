package jpabook.jpashop;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.repository.CategoryRepository;
import jpabook.jpashop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
@Transactional
public class TestCase {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;

    @Autowired
    EntityManager em;


    @Transactional
    public void Insert() {

        Category category = new Category();
        category.setTitle("TEST_CA");
        category.setSlug("1111");
        category.setMegaTitle("dddd");
        categoryRepository.save(category);

    }

}