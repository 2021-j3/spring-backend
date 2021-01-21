package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

}
