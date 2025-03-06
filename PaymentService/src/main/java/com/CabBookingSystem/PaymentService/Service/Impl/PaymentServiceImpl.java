package com.CabBookingSystem.PaymentService.Service.Impl;

import com.CabBookingSystem.PaymentService.DTO.PaymentDto;
import com.CabBookingSystem.PaymentService.Entity.Payment;
import com.CabBookingSystem.PaymentService.Repo.PaymentRepository;
import com.CabBookingSystem.PaymentService.Service.PaymentService;
import com.CabBookingSystem.PaymentService.Util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public PaymentDto processPayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString()); // Generate unique ID
        payment.setBookingId(paymentDto.getBookingId());
        payment.setUserId(paymentDto.getUserId());
        payment.setAmount(paymentDto.getAmount());
        payment.setStatus(Status.PENDING);

        Payment savedPayment = paymentRepository.save(payment);


        savedPayment.setStatus(Status.SUCCESS);
        paymentRepository.save(savedPayment);


        kafkaTemplate.send("payment-success", "Payment successful for Booking ID: " + savedPayment.getBookingId());
        kafkaTemplate.send("notification-topic", "User " + savedPayment.getUserId() + " payment of " + savedPayment.getAmount() + " is successful.");

        return new PaymentDto(
                savedPayment.getId(),
                savedPayment.getBookingId(),
                savedPayment.getUserId(),
                savedPayment.getAmount(),
                savedPayment.getStatus()
        );
    }
    @Override
    public Optional<PaymentDto> getPayment(String id) {

        return paymentRepository.findById(id)
                .map(payment -> new PaymentDto(
                        payment.getId(),
                        payment.getBookingId(),
                        payment.getUserId(),
                        payment.getAmount(),
                        payment.getStatus()
                ));
    }
}
