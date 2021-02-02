package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.controller.dto.BodyData;

public interface CrudInterface<Req, Res> {
    // POST
    BodyData<Res> create(Req request);

    // GET
    BodyData<Res> read(Long id);

    // PUT
    BodyData<Res> update(Req request);

    // DELETE
    BodyData delete(Long id);
}
