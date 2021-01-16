package jpabook.jpashop.repository;
import jpabook.jpashop.domain.*;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;

@Repository
@Transactional
public class AccountRepository {

    @PersistenceContext
    private EntityManager em; // Spring Boot return Entity Manager automatically.

    public void save(Account account) {
        em.persist(account);
    }
    public Account findOne(Long id){
        return em.find(Account.class,id);
    }
    public List<Account> findAll(){
        return em.createQuery("select a from Account a",Account.class).getResultList();
    }
}
