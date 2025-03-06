package com.CabBookingSystem.PaymentService.Controller;

import com.CabBookingSystem.PaymentService.DTO.PaymentDto;
import com.CabBookingSystem.PaymentService.Service.Impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @PostMapping
    public PaymentDto processPayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.processPayment(paymentDto);
    }

    @GetMapping("/{id}")
    public Optional<PaymentDto> getPayment(@PathVariable String id) {
        return paymentService.getPayment(id);
    }
}