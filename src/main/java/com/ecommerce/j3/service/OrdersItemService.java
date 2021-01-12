package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.OrdersItem;
import com.ecommerce.j3.repository.OrdersItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersItemService {
    private OrdersItemRepository ordersItemsRepository;

    @Autowired
    public OrdersItemService(OrdersItemRepository ordersItemsRepository){this.ordersItemsRepository = ordersItemsRepository;}

    public OrdersItem save(OrdersItem ordersItems){
        ordersItemsRepository.save(ordersItems);
        return ordersItems;
    }

    public OrdersItem update(OrdersItem ordersItems){
        ordersItemsRepository.save(ordersItems);
        return ordersItems;
    }

    public Optional<OrdersItem> findOneById(Long ordersItemId){
        return ordersItemsRepository.findById(ordersItemId);
    }

    public List<OrdersItem> findAll(){
        return ordersItemsRepository.findAll();
    }

    public void remove(OrdersItem ordersItems){
        ordersItemsRepository.delete(ordersItems);
    }
}
