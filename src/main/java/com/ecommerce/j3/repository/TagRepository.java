package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByTagIdIn(List<Long> tagIds);
    Set<Tag> getByTagIdIn(Set<Long> tagIds);
}
