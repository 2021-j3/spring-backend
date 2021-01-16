package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue
    private long product_id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
    private String title;
    private String meta_title;
    private String slug;

    private String summary;
    private int quantity;
    private float price;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime published_at;

    private LocalDateTime start_at;
    private LocalDateTime end_at;
    private String content;

}
