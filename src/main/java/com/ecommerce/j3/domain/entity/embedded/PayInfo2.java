package com.ecommerce.j3.domain.entity.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

// 카트에서 재사용 해도 될듯
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayInfo2 implements Cloneable{

    private Integer itemPriceTotal;

    private Integer itemDiscount;

    private Integer tax;

    private Integer shipping;

    private Integer userDiscount;

    private Integer grandTotal;

    @Transient
    private Integer quantity;

    public PayInfo2(Integer itemPriceTotal, Integer itemDiscount){
        this(itemPriceTotal,itemDiscount,0,0,0,itemPriceTotal-itemDiscount);
    }

    public PayInfo2(Integer itemPriceTotal,Integer itemDiscount,
                    Integer tax, Integer shipping, Integer userDiscount){
        this(itemPriceTotal,itemDiscount,tax,shipping,itemDiscount,
                itemPriceTotal
                        + tax
                        + shipping
                        - itemDiscount
                        - userDiscount);
    }

    public PayInfo2(Integer itemPriceTotal,Integer itemDiscount,
                    Integer tax, Integer shipping, Integer userDiscount, Integer grandTotal){
        validate(itemPriceTotal,itemDiscount,tax,shipping,userDiscount,grandTotal);
        this.itemPriceTotal = itemPriceTotal;
        this.itemDiscount = itemDiscount;
        this.tax = tax;
        this.shipping = shipping;
        this.userDiscount = userDiscount;
        this.grandTotal = grandTotal;
    }

    private void validate(Integer itemPriceTotal,Integer itemDiscount,
                          Integer tax, Integer shipping, Integer userDiscount, Integer grandTotal){
        if(grandTotal != itemPriceTotal
                + tax
                + shipping
                - itemDiscount
                - userDiscount) throw new IllegalArgumentException();
    }

    //  클론을 구현하는 이유
    //  https://joont92.github.io/jpa/%EA%B0%92-%ED%83%80%EC%9E%85/
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
