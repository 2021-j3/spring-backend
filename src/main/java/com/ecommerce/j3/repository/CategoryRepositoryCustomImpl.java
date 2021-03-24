package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.System;

import static java.lang.System.console;
import static java.lang.System.identityHashCode;

public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Long insert(Category category) {
        //초기화
        Integer leftBound = category.getParent() == null ? 0 : category.getParent().getRightBound() + 1;
        Integer rightBound = leftBound + 1;
        // 삽입
        em.persist(category);
        // slug, bound 설정
        Long id = (Long)em.createQuery("SELECT max(c.categoryId) from Category c").getSingleResult();
        String slug = "/categories/"+id;
        em.createNativeQuery("UPDATE category SET slug=:slug left_bound=:leftBound right_bound=:rightBound WHERE category_id=:id")
                .setParameter("id", id)
                .setParameter("slug", slug)
                .setParameter("leftBound", leftBound)
                .setParameter("rightBound", rightBound)
                .executeUpdate();
        // bound 갱신
        em.createNativeQuery("UPDATE Category SET right_bound = right_bound + 2 WHERE right_bound > :newRight")
                .setParameter("newRight", rightBound).executeUpdate();
        em.createNativeQuery("UPDATE Category SET left_bound = left_bound + 2 WHERE left_bound > :newLeft")
                .setParameter("newLeft", leftBound).executeUpdate();
        return id;
    }

    @Override
    @Transactional
    public void deleteByIdCustom(Long categoryId){
        Category category = em.find(Category.class, categoryId);
        deleteCustom(category);
    }

    @Transactional
    public void deleteCustom(Category category){
        // bound 임시저장
        Integer leftBound = category.getLeftBound();
        Integer rightBound = category.getRightBound();
        em.createNativeQuery("DELETE FROM category WHERE category_id = :id")
                .setParameter("id", category.getCategoryId()).executeUpdate();
        // bound 갱신
        em.createNativeQuery("UPDATE Category SET right_bound = right_bound - 2 WHERE right_bound > :oldRight")
                .setParameter("oldRight", rightBound).executeUpdate();
        em.createNativeQuery("UPDATE Category SET left_bound = left_bound - 2 WHERE left_bound > :oldLeft")
                .setParameter("oldLeft", leftBound).executeUpdate();
    }
}
