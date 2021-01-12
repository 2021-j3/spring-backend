package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.*;
import com.ecommerce.j3.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    private OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository){this.ordersRepository = ordersRepository;}

    public Orders save(Orders orders){
        ordersRepository.save(orders);
        return orders;
    }

    public Orders update(Orders orders){
        ordersRepository.save(orders);
        return orders;
    }

    public Optional<Orders> findOneById(Long orderId){
        return ordersRepository.findById(orderId);
    }

    public List<Orders> findByAccount(Account account){return ordersRepository.findByAccount(account);}

    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    public void remove(Orders orders){
        ordersRepository.delete(orders);
    }
}
