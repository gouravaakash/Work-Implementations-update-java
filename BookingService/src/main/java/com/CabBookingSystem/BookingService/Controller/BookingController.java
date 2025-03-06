package com.CabBookingSystem.BookingService.Controller;

import com.CabBookingSystem.BookingService.DTO.BookingRequestDTO;
import com.CabBookingSystem.BookingService.DTO.BookingResponseDTO;
import com.CabBookingSystem.BookingService.Services.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingServices bookingServices;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO request,double distane) {
        BookingResponseDTO response = bookingServices.createBooking(request, distane);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable String bookingId) {
        BookingResponseDTO response = bookingServices.getBookingById(bookingId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        List<BookingResponseDTO> bookings = bookingServices.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(@PathVariable String userId) {
        List<BookingResponseDTO> bookings = bookingServices.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable String bookingId) {
        bookingServices.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking canceled successfully.");
    }
}
