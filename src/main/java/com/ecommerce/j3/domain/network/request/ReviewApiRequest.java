package com.ecommerce.j3.domain.network.request;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewApiRequest {
    private Long reviewId;
    private Review parent;
    private OrderItem orderItem;
    private Account account;
    private Integer rate; // set 1
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
