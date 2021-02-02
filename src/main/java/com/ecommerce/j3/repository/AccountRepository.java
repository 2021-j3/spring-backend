//package com.ecommerce.j3.repository;
//
//import com.ecommerce.j3.domain.entity.Account;
//import com.ecommerce.j3.domain.entity.Order;
//import com.ecommerce.j3.domain.entity.Product;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@RequiredArgsConstructor
//@Slf4j
//public class AccountRepository {
//
//   // @PersistenceContext  => @Autowired 로 spring data jpa가 바꿔 처리해줌
//    private final EntityManager em;
//
//    // 이거 안해도 되는 부분
//    public Account save(Account account){
//        if(account.getAccountId() == null){
//            log.info("PERSIST@");
//            em.persist(account);   // save
//        }else{
//            log.info("MERGE@");
//            em.merge(account); // update
//        }
//        return  account;
//    }
//    public Account findOne(Long id){
//        return em.find(Account.class, id);
//    }
//    public List<Account> findAll(){
//        return em.createQuery("select a from Account a", Account.class).getResultList();
//    }
//    public Account findByEmail(String email){
//        return em.createQuery("select a from Account a where a.email=:email", Account.class)
//                .setParameter("email",email).getSingleResult();
//    }
//
//    public Optional<Account> findById(Long id) {
//        return em.createQuery("select a from Account a where a.accountId=:id", Account.class)
//                .setParameter("id", id)
//                .getResultList()
//                .stream()
//                .findFirst();
//    }
//
//    public void delete(Account account) {
//        em.remove(account);
//    }
//}
package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByEmail(String email);
}
