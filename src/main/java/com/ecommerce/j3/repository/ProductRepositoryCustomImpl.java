package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long insert(Product product){
        em.persist(product);
        Long id = (Long)em.createQuery("SELECT max(p.productId) from Product p").getSingleResult();
        String slug = "/products/" + id;
        em.createNativeQuery("UPDATE product SET slug=:slug WHERE product_id=:id")
                .setParameter("id", product.getProductId())
                .setParameter("slug", slug)
                .executeUpdate();
        return id;
    }
}
