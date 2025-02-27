package com.CabBookingSystem.BookingService.Services;


import com.CabBookingSystem.BookingService.DTO.BookingRequestDTO;
import com.CabBookingSystem.BookingService.DTO.BookingResponseDTO;

import java.util.List;

public interface BookingServices {
     BookingResponseDTO createBooking(BookingRequestDTO request);
     BookingResponseDTO getBookingById(String bookingId);
     List<BookingResponseDTO> getAllBookings();
     List<BookingResponseDTO> getBookingsByUserId(String userId);
     void cancelBooking(String bookingId);



}
