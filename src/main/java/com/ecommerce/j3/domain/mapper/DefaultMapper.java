package com.ecommerce.j3.domain.mapper;

public interface DefaultMapper<E, Req, Res> {
    public void updateFromDto(E entity, Req dto);
    public E toEntity(Req dto);

    public Res toDto(E entity);
}
