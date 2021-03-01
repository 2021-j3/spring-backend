package com.ecommerce.j3.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Amount {
    private Integer total, tax_free, vat, point, discount;
}
