package com.CabBookingSystem.BookingService.DTO;

import com.CabBookingSystem.BookingService.Entity.Booking;
import com.CabBookingSystem.BookingService.Util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO
{
    private String bookingId;
    private String Status;
    private LocalDateTime bookingTime;
    private String pickupLocation;
    private String dropoffLocation;

    public BookingResponseDTO(Booking booking) {
        this.bookingId = booking.getBookingId();
        this.Status = booking.getStatus().toString();
        this.bookingTime = booking.getBookingTime();
        this.pickupLocation = booking.getPickupLocation();
        this.dropoffLocation = booking.getDropoffLocation();
    }
}
