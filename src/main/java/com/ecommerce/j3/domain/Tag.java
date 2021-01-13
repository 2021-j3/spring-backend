package com.ecommerce.j3.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String title;
    private String meta_title;
    private String slug;
    @Column(columnDefinition = "TEXT")
    private String content;
}
