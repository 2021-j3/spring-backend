package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ProductCategory {
    @Id @GeneratedValue
    private Long productCategoryId;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
