package com.CabBookingSystem.BookingService.Entity;

import com.CabBookingSystem.BookingService.Util.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String bookingId = UUID.randomUUID().toString();
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String cabId;
    @Column(nullable = false)
    private LocalDateTime bookingTime;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private String pickupLocation;
    @Column(nullable = false)
    private String dropoffLocation;
    @Column(nullable = false)
    private double amount;

}
