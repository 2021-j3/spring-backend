package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Product;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final EntityManager em;

    public void save(Product product){
        if(product.getProductId() != null){
            em.persist(product);   // save
        }else{
            em.merge(product); // update
        }
    }

    public Product findOne(Long id){
        return em.find(Product.class,id);
    }

    public List<Product> findAll(){
        return em.createQuery("select p from Product p",Product.class).getResultList();

    }
}
