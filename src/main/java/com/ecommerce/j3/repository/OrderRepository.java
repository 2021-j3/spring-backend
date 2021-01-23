package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository{
   private final EntityManager em;

   public void save(Order order){
       em.persist(order);
   }
   public Order findOne(Long id){
       return em.find(Order.class,id);
   }
/*
   public List<Order> findAll(OrderSearch orderSearch){
       em.createQuery("select o from Order o join o.account a",Order.class);

   }
   */

}
