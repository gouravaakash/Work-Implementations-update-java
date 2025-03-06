package com.CabBookingSystem.BookingService.DTO;

import com.CabBookingSystem.BookingService.Entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String userId;
    private String cabId;
    private LocalDateTime bookingTime;
    private String status;
    private String pickupLocation;
    private String dropoffLocation;
    private double amount ;

    public BookingDTO(Booking booking){
        this.userId = booking.getUserId();
        this.cabId=booking.getCabId();
        this.bookingTime=booking.getBookingTime();
        this.status = booking.getStatus().toString();
        this.pickupLocation= booking.getPickupLocation();
        this.dropoffLocation= booking.getDropoffLocation();
        this.amount = booking.getAmount();
    }

}
