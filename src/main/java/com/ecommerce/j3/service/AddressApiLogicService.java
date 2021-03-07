package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.mapper.AddressMapper;
import com.ecommerce.j3.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional(readOnly = true)
@Transactional
@RequiredArgsConstructor
public class AddressApiLogicService {
    private final DefaultAddressApiLogicService defaultAddressApiLogicService;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressApiResponse saveAddress(AddressApiRequest request) {
        Address address = addressMapper.toEntity(request);
        addressRepository.save(address);
        // 기본 배송지가 없으면 기본 배송지로 지정
        return addressMapper.toApiResponse(address);
    }

    public AddressApiResponse updateAddress(AddressApiRequest request) {
        Address addressFromDB = findById(request.getAddressId());
        addressMapper.updateFromDto(addressFromDB, request);
        addressRepository.save(addressFromDB);
        return addressMapper.toApiResponse(addressFromDB);
    }

    // TODO: 사실 쓸 일이 없다, 삭제해도 무방
    public AddressApiResponse findAddress(Long addressId) {
        Address addressFromDB = findById(addressId);
        return addressMapper.toApiResponse(addressFromDB);
    }

    public List<AddressApiResponse> findMyAddresses(Long accountId){
        Address defaultAddress = defaultAddressApiLogicService.findByAccountId(accountId);
        // 기본 배송지 표시
        return addressRepository.findByAccountId(accountId)
                .stream().map(address -> {
                    AddressApiResponse response = addressMapper.toApiResponse(address);
                    if (defaultAddress != null)
                        response.setThisIsDefault(address.getAddressId().equals(defaultAddress.getAddressId()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public void removeAddress(Long id) {
        Long accountId = findById(id).getAccount().getAccountId();
        addressRepository.deleteById(id);
        // 기본 배송지가 없는 경우
        updateDefaultAddress(accountId);
    }

    public void updateDefaultAddress(Long accountId){
        if (defaultAddressApiLogicService.findDefaultAddressOfAccount(accountId) == null)
            findMyAddresses(accountId).stream().findFirst().ifPresent(addressApiResponse -> {
                defaultAddressApiLogicService.saveMyDefaultAddress(accountId,addressApiResponse.getAddressId());
            });
    }

    // 패키지 한정자, service패키지 내에서만 접근 가능
    Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }
}
