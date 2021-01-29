package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.domain.network.BodyData;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CrudInterface<Req, Res> {
    BodyData<Res> create(BodyData<Req> request) throws JsonProcessingException;

    BodyData<Res> read(Long id);

    BodyData<Res> update(BodyData<Req> request);

    BodyData delete(Long id);
}
