package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Category;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.entity.Tag;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class ProductSpecs {
    /**
     * 2021-02-16 penguin418 querydsl
     * 띄어쓰기로 분류하여 keywords 를 구하고 이를 만족하는 결과를 리턴
     * a b c
     * @param keywords
     * @return
     */
    public static Specification<Product> withKeywords(String keywords) {
        return (Specification<Product>) ((root, query, builder) -> {
            Predicate predicate = builder.conjunction();    // 모두 허용
            if (keywords == null) return predicate;         // 예외
            String spaceWithoutQuotes = "\\s*(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
            for (String keyword : keywords.split(spaceWithoutQuotes)) { // 키워드 들에 대해서
                keyword = keyword.replaceAll("\"", "");
                predicate = builder.and(predicate, builder.like(root.get("title"),
                        "%" + keyword + "%"));
            }
            return predicate;
        });
    }

    public static Specification<Product> fromPrice(Integer minPrice) {
        return (Specification<Product>) ((root, query, builder) ->
                minPrice == null
                        ? builder.conjunction()
                        : builder.greaterThanOrEqualTo(root.get("price"), minPrice));
    }

    public static Specification<Product> toPrice(Integer maxPrice) {
        return (Specification<Product>) ((root, query, builder) ->
                maxPrice == null
                        ? builder.conjunction()
                        : builder.lessThanOrEqualTo(root.get("price"), maxPrice));
    }

    public static Specification<Product> fromCategories(List<Category> categories){
        return (Specification<Product>) ((root, query, builder) ->{
            Predicate predicate = builder.conjunction();
            if (categories == null) return predicate;         // 예외
            return root.join("categories").in(categories);
        });
    }

    public static Specification<Product> fromTags(List<Tag> tags){
        return (Specification<Product>) ((root, query, builder) ->{
            Predicate predicate = builder.conjunction();
            if (tags == null) return predicate;         // 예외
            return root.join("tags").in(tags);
        });
    }

}
