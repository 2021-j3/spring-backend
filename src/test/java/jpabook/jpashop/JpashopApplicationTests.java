package jpabook.jpashop;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.Product;
import jpabook.jpashop.domain.ProductCategory;
import jpabook.jpashop.repository.CategoryRepository;
import jpabook.jpashop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
public class JpashopApplicationTests {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	CategoryService categoryService;

	@Test
	@Commit
	public void Insert() {
		Category category = new Category();
		category.setTitle("TEST_CA");
		categoryService.login(category);

	}

}
