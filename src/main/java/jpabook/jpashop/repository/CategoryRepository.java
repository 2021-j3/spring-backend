package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Account;
import jpabook.jpashop.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class CategoryRepository {

    @PersistenceContext
    private EntityManager em ;
    // Spring Boot return Entity Manager automatically.

    public void save(Category category) {
        em.persist(category);
    }
    public Category findOne(Long id){
        return em.find(Category.class,id);
    }
    public List<Category> findAll(){
        return em.createQuery("select c from Category c",Category.class).getResultList();
    }
}
