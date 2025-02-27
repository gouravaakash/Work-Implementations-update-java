package com.CabBookingSystem.BookingService.DTO;

import com.CabBookingSystem.BookingService.Entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    private String pickupLocation;
    private String dropoffLocation;

    public BookingRequestDTO(Booking booking) {
        this.pickupLocation =booking.getPickupLocation();
        this.dropoffLocation = booking.getDropoffLocation();
    }
}
