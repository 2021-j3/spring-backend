package com.ecommerce.j3.controller.dto;

public interface DefaultMapper<E, Req, Res> {
    public E toEntity(Req dto);

    public Res toApiResponseDto(E entity);

    public void updateFromDto(E entity, Req dto);
}
