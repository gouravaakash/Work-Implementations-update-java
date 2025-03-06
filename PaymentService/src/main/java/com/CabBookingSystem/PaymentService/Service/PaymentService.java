package com.CabBookingSystem.PaymentService.Service;

import com.CabBookingSystem.PaymentService.DTO.PaymentDto;
import com.CabBookingSystem.PaymentService.Repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface PaymentService {
    PaymentDto processPayment(PaymentDto paymentDto);
    Optional<PaymentDto> getPayment(String id);
}
