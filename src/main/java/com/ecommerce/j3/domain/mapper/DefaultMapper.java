package com.ecommerce.j3.domain.mapper;

public interface DefaultMapper<E, Req, Res> {
    public E toEntity(Req dto);

    public Res toApiResponse(E entity);

    public E updateFromDto(E entity, Req dto);
}
