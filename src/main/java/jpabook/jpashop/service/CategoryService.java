package jpabook.jpashop.service;

import jpabook.jpashop.domain.Account;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.repository.AccountRepository;
import jpabook.jpashop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private  CategoryRepository categoryRepository;


    public Long login(Category category){
        categoryRepository.save(category);
        return category.getCategoryId();
    }
}
