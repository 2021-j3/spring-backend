package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.PaymentDto;
import com.ecommerce.j3.domain.entity.Payment;
import com.ecommerce.j3.domain.mapper.PaymentMapper;
import com.ecommerce.j3.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentApiLogicService {
    private PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentDto.PaymentApiResponse saveService(PaymentDto.PaymentApiRequest request) {
        Payment payment = paymentMapper.toEntity(request);
        paymentRepository.save(payment);
        return paymentMapper.toApiResponse(payment);
    }

//    public Payment update(Payment payment){
//        paymentRepository.save(payment);
//        return payment;
//    }
//
//    public Optional<Payment> findOne(Long paymentId){
//        return paymentRepository.findById(paymentId);
//    }
//
//    public List<Payment> findByAccount(Account account){return paymentRepository.findByAccount(account);}
//
//    public List<Payment> findAll(){
//        return paymentRepository.findAll();
//    }
//
//    public void remove(Payment payment){
//        paymentRepository.delete(payment);
//    }

}
