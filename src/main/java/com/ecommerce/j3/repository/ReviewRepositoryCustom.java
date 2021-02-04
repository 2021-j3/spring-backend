package com.ecommerce.j3.repository;

import java.time.LocalDateTime;
// https://www.baeldung.com/spring-data-jpa-query
// 위 페이지의 9.2 를 참조하세요
// 이 인터페이스는 jpa 라이브러리가 ~impl 클래스에 구현한 메소드를 가져다 사용하도록 하여
// jpa에
public interface ReviewRepositoryCustom {
    Long save(Long reviewId, Long parentId, Long orderItemId, Long accountId, Integer rate, String title, String content, LocalDateTime createdAt);
}
