package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    private Long categoryId;

    //@ManyToOne
  //  @JoinColumn(name="parent_id")
    private Long parentId;

    private String title;
    private String megaTitle;
    private String slug;
    private String content;



}
