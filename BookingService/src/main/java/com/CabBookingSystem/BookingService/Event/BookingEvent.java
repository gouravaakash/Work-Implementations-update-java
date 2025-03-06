package com.CabBookingSystem.BookingService.Event;

import com.CabBookingSystem.BookingService.Util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookingEvent {
    private String bookingId;
    private String status;
    private String pickupLocation;
    private String dropoffLocation;
    private double amount;

    public BookingEvent(String bookingId, String status, String pickupLocation, String dropoffLocation, double amount) {
        this.bookingId = bookingId;
        this.status = status;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.amount = amount;
    }
}
