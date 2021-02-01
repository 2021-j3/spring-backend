package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.network.BodyData;
import com.ecommerce.j3.domain.network.request.CartApiRequest;
import com.ecommerce.j3.domain.network.response.CartApiResponse;

public class CartApiController implements CrudInterface<CartApiRequest, CartApiResponse> {
    @Override
    public BodyData<CartApiResponse> create(CartApiRequest request) {
        return null;
    }

    @Override
    public BodyData<CartApiResponse> read(Long id) {
        return null;
    }

    @Override
    public BodyData<CartApiResponse> update(CartApiRequest request) {
        return null;
    }

    @Override
    public BodyData delete(Long id) {
        return null;
    }
}
