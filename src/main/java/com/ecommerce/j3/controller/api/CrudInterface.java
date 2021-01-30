package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.domain.network.BodyData;

public interface CrudInterface<Req, Res> {
    // POST
    BodyData<Res> create(BodyData<Req> request);

    // GET
    BodyData<Res> read(Long id);

    // PUT
    BodyData<Res> update(BodyData<Req> request);

    // DELETE
    BodyData delete(Long id);
}
