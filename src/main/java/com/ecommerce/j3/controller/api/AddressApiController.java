package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.domain.network.BodyData;
import com.ecommerce.j3.domain.network.request.AddressApiRequest;
import com.ecommerce.j3.domain.network.response.AccountApiResponse;
import com.ecommerce.j3.domain.network.response.AddressApiResponse;
import org.springframework.stereotype.Controller;

@Controller
public class AddressApiController implements CrudInterface<AddressApiRequest, AddressApiResponse> {

    @Override
    public BodyData<AddressApiResponse> create(AddressApiRequest request) {
        return null;
    }

    @Override
    public BodyData<AddressApiResponse> read(Long id) {
        return null;
    }

    @Override
    public BodyData<AddressApiResponse> update(AddressApiRequest request) {
        return null;
    }

    @Override
    public BodyData delete(Long id) {
        return null;
    }
}
