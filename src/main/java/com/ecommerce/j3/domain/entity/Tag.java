package com.ecommerce.j3.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    private String title;

    private String metaTitle;

    private String slug;

    @Column(columnDefinition = "TEXT")
    private String content;
}
