package com.ecommerce.j3.domain.entity;

import com.ecommerce.j3.controller.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)    // 01-18 Megan
    public List<OrderItem> orderItems ;

    private String sessionId;

    private String token;

    @Column(columnDefinition = "enum('READY', 'ORDER', 'CANCEL')")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Integer itemPriceTotal;

    private Integer itemDiscount;

    private Integer tax;

    private Integer shipping;

    private Integer userDiscount;

    private Integer grandTotal;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String roadAddress;;

    private String address;

    private String city;

    private String province;

    private String country;

    private Integer zipCode;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(updatable=false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // FIXME: Order 를 만드는 함수가 인스턴스 메소드인 것은 아주 안 좋은 선택 같습니다
    public Order createOrder(Account account, Product product, OrderItem... orderItems) {
        Order order = Order.builder()
                .account(account)
                .status(OrderStatus.READY)
                .itemPriceTotal(1)
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .phoneNumber(account.getPhoneNumber())
                .address(String.valueOf(account.getAddresses()))
                // FIXME: 문제가 있습니다
                //   - 1. account.getAddresses() 는 주소 리스트를 반환합니다
                //   - 2. 인티티 설계에서 어드레스의 id가 아니라, 모든 필드를 넣도록 설계했기 때문에
                //   - 모든 필드를 하나하나 대입하도록 해야 합니다.
                .roadAddress("Test")
//                .address(String.valueOf(account.getAddresses()))
//                .city(account.getDefaultAddress().getCity())
                .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
//        System.out.println("This is " + order.getAddress());
        return order;
    }

    // orderItem 삭제
//    public Order createOrder(Account account, Product product) {
//        Order order = Order.builder()
//                .account(account)
//                .status(OrderStatus.READY)
//                .itemPriceTotal(1)
//                .firstName(account.getFirstName())
//                .lastName(account.getLastName())
//                .phoneNumber(account.getPhoneNumber())
//                .city(account.getDefaultAddress().getCity())
//                .build();
//
//        return order;
//    }


//        Product product = new Product();
//
//        OrderItem orderItem = OrderItem.builder()
//                .order(order)
//                .build();

//    public void addOrderItem(OrderItem orderItem){
//        orderItems.add(orderItem);
//        orderItem.setOrder(this);
//    }
//
//    //** 생성 메서드 **//
//    public  Order (Account account) {
//      this.account = account;
//    }
//
//    //** 비즈니스 로직 **//
//    // 주문 취소
//    public void cancel(){
//        this.setStatus((short)2);
//        this.setStatus(OrderStatus.CANCEL);
//        for(OrderItem orderItem:orderItems){
//            orderItem.cancel();
//        }
//    }
//
//    //  주문 조회
//    public Integer getGrandTotal(){
//        Integer totalPrice  = 0;
//        for(OrderItem orderItem: orderItems){
//            grandTotal += orderItem.getTotalPrice();
//        }
//        return totalPrice;
//    }
//
//    public void setStatus(OrderStatus status){
//        this.status = status;
//    }
}
