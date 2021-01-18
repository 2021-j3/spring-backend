//package com.ecommerce.j3.domain.repository;
//
//import com.ecommerce.j3.J3ApplicationTests;
//import com.ecommerce.j3.domain.entity.ProductTag;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.*;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ProductTagRepositoryTest extends J3ApplicationTests {
//    @Autowired
//    private ProductTagRepository productTagRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private TagRepository tagRepository;
//
//    @Transactional
//    @Test
//    public void create(){
//        ProductTag productTag = new ProductTag();
//
////        productTag.setProduct(productRepository.getOne(2L));
////        productTag.setTag(tagRepository.getOne(2L));
////
////        ProductTag newProductTag = productTagRepository.save(productTag);
////
////        Assert.assertNotNull(newProductTag);
//    }
//
//}