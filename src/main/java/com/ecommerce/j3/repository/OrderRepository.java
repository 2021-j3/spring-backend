package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.entity.OrderStatus;
import com.ecommerce.j3.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepository{
   private final EntityManager em;

   /* 주문 생성 비지니스 로직 */
    public Order makeOrder(Account account,OrderItem... orderItems){
        Order order = Order.builder().
        sessionId("will be filled").
        roadAddress("will be filled").
        address("will be filled").
        city("will be filled").
        content("what to write").
        country("will be filled").
        province("will be filled").
        zipCode(10 /* will be modified */).
        lastName(account.getLastName()).
        firstName(account.getFirstName()).
        createdAt(LocalDateTime.now()).
        email(account.getEmail()).
       // grandTotal(100).
     //   itemDiscount(1).
       // tax(1).
     //   itemPriceTotal(111).
        phoneNumber(account.getPhoneNumber()).
       // shipping(1).
        token("will be filled").
      //  userDiscount(1).
        build();

        order.orderItems = new ArrayList<OrderItem>();
        addOrderItems(order, orderItems);
        order.setStatus(OrderStatus.ORDER);
        em.persist(order);
        return order;
    }

    private void addOrderItems(Order order,OrderItem... orderItems){
        for (OrderItem orderItem : orderItems) {
            order.orderItems.add(orderItem);
            orderItem.setOrder(order);
        }
    }






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
