package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepository   {

    // @PersistenceContext  => @Autowired 로 spring data jpa가 바꿔 처리해줌
    private final EntityManager em;

    public void save(Address address) {
        em.persist(address);
    }


}