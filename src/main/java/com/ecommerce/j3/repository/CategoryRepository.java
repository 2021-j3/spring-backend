package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
    List<Category> findByCategoryIdIn(List<Long> categoryIds);
    Set<Category> getByCategoryIdIn(Set<Long> categoryIds);

    /**
     * 삭제시 카테고리 nested set 갱신 필요
     * ( save의 경우 update에도 사용되므로 default로 지정 안함 )
     * @param id
     */
    public default void deleteById(Long id){
        deleteByIdCustom(id);
    }
}
