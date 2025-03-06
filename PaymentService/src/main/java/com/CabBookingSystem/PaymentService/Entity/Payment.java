package com.CabBookingSystem.PaymentService.Entity;

import com.CabBookingSystem.PaymentService.Util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    private String bookingId;
    private String userId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Status status;




}
