package com.CabBookingSystem.PaymentService.DTO;

import com.CabBookingSystem.PaymentService.Util.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String id;
    private String bookingId;
    private String userId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Status status;

}
