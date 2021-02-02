package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.PaymentDto;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Payment;
import com.ecommerce.j3.domain.entity.Payment;
import com.ecommerce.j3.domain.mapper.PaymentMapper;
import com.ecommerce.j3.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {
    private PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentDto.PaymentApiResponse save(PaymentDto.PaymentApiRequest request){
        Payment payment = paymentMapper.toEntity(request);
        paymentRepository.save(payment);
        return paymentMapper.toDto(payment);
    }
    
    public Payment save(Payment payment){
        paymentRepository.save(payment);
        return payment;
    }

    public Payment update(Payment payment){
        paymentRepository.save(payment);
        return payment;
    }

    public Optional<Payment> findOne(Long paymentId){
        return paymentRepository.findById(paymentId);
    }

    public List<Payment> findByAccount(Account account){return paymentRepository.findByAccount(account);}

    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }

    public void remove(Payment payment){
        paymentRepository.delete(payment);
    }
}
