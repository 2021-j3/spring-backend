package com.ecommerce.j3.domain.entity.embedded;

import com.ecommerce.j3.domain.entity.OrderItem;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class OrderDetails {

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public OrderDetails(List<OrderItem> items) {
        this.items = items;
    }

    // 하나짜리 리스트
    public OrderDetails(OrderItem orderItem){
        this.items = Collections.singletonList(orderItem);
    }

    // 수정불가능한 컬렉션 리턴
    public List<OrderItem> getItems(){
        return List.copyOf(this.items);
    }

    public PayInfo2 calculcatePayment() {
        Integer itemPriceTotal = 0;
        Integer itemDiscount = 0;

        for (OrderItem item : items) {
            itemPriceTotal += item.getPrice() * item.getQuantity();
            itemDiscount += item.getDiscountPrice() * item.getQuantity();
        }

        return new PayInfo2(itemPriceTotal,itemDiscount);
    }
}
