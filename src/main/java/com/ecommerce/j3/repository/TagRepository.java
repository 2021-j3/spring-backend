package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
