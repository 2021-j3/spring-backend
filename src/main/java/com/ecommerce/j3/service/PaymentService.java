package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Payment;
import com.ecommerce.j3.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository){this.paymentRepository = paymentRepository;}

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
