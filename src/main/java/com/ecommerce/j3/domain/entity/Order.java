package com.ecommerce.j3.domain.entity;

import com.ecommerce.j3.domain.entity.embedded.OrderDetails;
import com.ecommerce.j3.domain.entity.embedded.PayInfo2;
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
//@Table (name = "ORDERS", schema = "SHOP")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy="order",cascade = CascadeType.ALL)    // 01-18 Megan
    public List<OrderItem> orderItems;

//    @Embedded
//    public OrderDetails orderDetails;

    private String sessionId;

    private String token;

    @Column(columnDefinition = "enum('READY', 'ORDER', 'CANCEL')")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

//    private Integer itemPriceTotal;
//
//    private Integer itemDiscount;
//
//    private Integer tax;
//
//    private Integer shipping;
//
//    private Integer userDiscount;
//
//    private Integer grandTotal;

    /**
     * FIXME: PayInfo 와 통합 필요
     *   1. getPriceDetails() 을 가져왔을 때 수정할 수 없어야 함
     *   - getPriceDetails().setDiscount 가 아니라, setDiscount 로 수정할 수 있도록 바꿔야 함
     *   - 모순적인 명세지만 가능한 해결책은 다음과 같음
     *     (1) Order.getPriceDetail() 이 copyOf 를 돌려줌
     *     - 장점: 수정 안됨, 단점: 수정 안되는데 수정되는 것처럼 느껴짐
     *     (2) Order.setDiscount() 하면 setter 없는 PriceDetail 객체를 만듦
     *     - 장점: 수정 안되며, 시도도 안됨, 단점: 매번 orderItems 를 순회해서 값들을 가져와야 함
     */
    @Embedded
    private PayInfo2 payInfo;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    /*
    * */
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

    //** 연관 관계 메서드 **//
//    public void setAccount(Account account){
//        this.account = account;
//        //account.getOrders().add(this);
//    }

    public void setOrderItems(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }

    public void setPayInfo(PayInfo2 payInfo){
        this.payInfo = payInfo;
    }

    public void setAccount(Account account){
        this.account = account;
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.phoneNumber = account.getPhoneNumber();
        this.email = account.getEmail();
        // FIXME: 컬럼 날려야됨
        this.sessionId = "";
        this.token = "";
    }

    public void setAddress(Address addressObj){
        this.address = addressObj.getAddress();
        this.roadAddress = addressObj.getRoadAddress();
        this.city = addressObj.getCity();
        this.province = addressObj.getProvince();
        this.country = addressObj.getCountry();
        this.zipCode = addressObj.getZipCode();
    }

    public void setReady() throws IllegalStateException{
        if(this.account == null || this.firstName == null || this.lastName == null || this.phoneNumber == null || (this.email == null)
                || (this.sessionId == null) || (this.token == null)
                || (this.payInfo == null)
                || (this.address == null) || (this.roadAddress == null) || (this.city == null) || (this.province == null) || (this.country == null) || (null == this.zipCode)) throw new IllegalStateException();
        this.status = OrderStatus.READY;
    }

    public void setOrdered(){
        // TODO: 상태 체크
        this.status = OrderStatus.ORDER;
    }

    public void setCancel(){
        // TODO: 상태 체크
        this.status = OrderStatus.CANCEL;
    }
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
////        this.setStatus((short)2);
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
