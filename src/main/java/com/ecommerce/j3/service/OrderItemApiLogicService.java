package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.OrderItemDto;
import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.mapper.OrderItemMapper;
import com.ecommerce.j3.repository.OrderItemRepository;
import com.ecommerce.j3.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemApiLogicService {
    @Autowired
    private final OrderItemRepository orderItemsRepository;

    @Autowired
    private final OrderRepository orderRepository;

    private final OrderItemMapper orderItemMapper;

    /* orderItems save */
    public OrderItemDto.OrderItemApiResponse createService(OrderItemDto.OrderItemApiRequest request){
        OrderItem orderItem = orderItemMapper.toEntity(request);
        try{
            if(request.getOrderId().equals(null)){
                throw new EntityNotFoundException("Not Found OrderId");
            }
            else{
                orderItemsRepository.save(orderItem);
                return orderItemMapper.toApiResponse(orderItem);
            }
        } catch (EntityNotFoundException e){
            throw  new EntityNotFoundException("Exception");
        }
    }

    /* 하나의 주문 목록 */
    public Optional<OrderItem> findOneService(Long orderItemsId) {
        return orderItemsRepository.findById(orderItemsId);
    }

    /* 모든 주문 목록 */
    public List<Order> findAllService(){
        return orderRepository.findAll();
    }

    /* orderItems 삭제 */
    public void removeService(OrderItem orderItems){
        orderItemsRepository.delete(orderItems);
    }
}
