package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.controller.dto.OrderItemDto;
import com.ecommerce.j3.domain.entity.*;
import com.ecommerce.j3.domain.entity.embedded.OrderDetails;
import com.ecommerce.j3.domain.mapper.OrderItemMapper;
import com.ecommerce.j3.domain.mapper.OrderMapper;
import com.ecommerce.j3.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Transactional
//@RequiredArgsConstructor
@Service
public class OrderApiLogicService {
    /* user 정보 확인 */
    private final AccountRepository accountRepository;

    /* 바로 주문하기 눌렀을 때 사용하기 위한 ProductRepository */
    private final ProductRepository productRepository;

    /* CartItem의 정보를 가져오기 위한 Repository */
    private final CartItemRepository cartItemRepository;

    /* order 저장을 위한 Repository */
    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private OrderMapper orderMapper;
    private OrderItemMapper orderItemMapper;

    public OrderApiLogicService(AccountRepository accountRepository, ProductRepository productRepository,
                                CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                                OrderMapper orderMapper, OrderItemMapper orderItemMapper
                                ){
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    /* Product에서 바로 저장 */
    public OrderDto.OrderApiResponse directSaveService(OrderDto.OrderApiRequest request) {
        Order order = orderMapper.toEntity(request);

        orderRepository.save(order);
        return orderMapper.toApiResponse(order);
    }

    /* CartItem에서 가져와서 저장*/
    public OrderDto.OrderApiResponse indirectSaveService(OrderDto.OrderApiRequest request) {
        Order order = orderMapper.toEntity(request);
        List<CartItem> cartItems = cartItemRepository.findAll();

        // TODO: 이후 로직은 뭘까???
        // TODO: 일단 보류

        orderRepository.save(order);
        return orderMapper.toApiResponse(order);
    }

    public OrderDto.OrderApiResponse updateUserService(OrderDto.OrderApiRequest request){
        if (accountRepository.getOne(request.getAccountId()) == null){
            throw new EntityNotFoundException("Not Found updateUser");
        }

        Order orderFromDB = orderRepository
                .findById(request.getOrdersId())
                .orElseThrow(() -> new EntityNotFoundException());

        Order updateDB = orderMapper.updateUserFromDto(orderFromDB, request);
        orderRepository.save(updateDB);
        return orderMapper.toApiResponse(updateDB);

    }

    public OrderDto.OrderApiResponse updatePayService(OrderDto.OrderApiRequest request){
        if (accountRepository.getOne(request.getAccountId()) == null){
            throw new EntityNotFoundException("Not Found updatePay");
        }
        Order orderFromDB = orderRepository
                .findById(request.getOrdersId())
                .orElseThrow(() -> new EntityNotFoundException());

        Order updateDB = orderMapper.updatePayFromDto(orderFromDB, request);
        orderRepository.save(updateDB);
        return orderMapper.toApiResponse(updateDB);
    }

    public OrderDto.OrderApiResponse findIdService(Long id) {
        // 1. id -> repository getOne / getById
        Order orderFromDB = orderRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        // 2. return account -> accountApiResponse
        return orderMapper.toApiResponse(orderFromDB);
    }

    public void deleteService(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDto.OrderApiResponse makeOrderFromProduct(Long accountId, OrderItemDto.CreateRequestFromProduct request) {
        Order order = new Order();

        // 어카운트 설정
        Account account = accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new);
        order.setAccount(account);

        // 주문 내용
        Product product = productRepository.findById(request.getProductId()).orElseThrow(EntityNotFoundException::new);
        OrderItem orderItem = OrderItem.FromProduct(product, request.getQuantity());
        orderItem.setOrder(order);
        // 카트 사용 시에는 아래 방법을 사용하면 될거임
        // for(OrderItem o: orderItems){ o.setOrder(order);}
        // OrderDetails orderDetails = new OrderDetails(orderItems)
        // ...
        OrderDetails orderDetails = new OrderDetails(orderItem);
        order.setOrderItems(orderDetails.getItems());
        order.setPayInfo(orderDetails.calculcatePayment());

        // 기타
        order.setAddress(account.getDefaultAddress());
        order.setReady();
        orderRepository.save(order);

        // FIXME: DB 접근 줄일 방법 ?
        //  1. account 의 경우,
        //  - 해당 하는 모든 필드를 대상으로 해시 -> versionID 생성
        //  - password 등 어차피 서버접근 필수
        //  2. product 의 경우,
        //  - productId, product, price, updatedAt 을 대상으로 해시 -> versionID 생성 후 DB 저장 및 클라에게 보내줌
        //  - 클라에서 받은 값으로 해시 <-> 클라에서 받은 versionID 비교 후, 다르면 서버 참조
        //  - 같으면 해당 값으로 orderItem 생성만 하면 됨
        //  - product 도 연관된 필드 많아서 힘들듯, -> jPQL로 id만 사용시 해결 가능

        // TODO: product.quantity
        //  READY, ORDER, CANCEL
        //  READY (결제 안한거) -> ORDER(결제 끝난거) -> CANCEL 취소,
        //  READY -> FAIL 실패
        //  1. 여기서 product.quantity 감소시킨 후, READY -> FAIL 때 product.quantity 복구
        //  2. READY -> ORDER 로 변할 때 product.quantity 감소시키기
        //  -> PAYMENT 단계에서 필요

        return orderMapper.toApiResponse(order);

    }
}