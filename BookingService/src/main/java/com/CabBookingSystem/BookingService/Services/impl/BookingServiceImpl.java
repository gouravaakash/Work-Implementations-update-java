package com.CabBookingSystem.BookingService.Services.impl;

import com.CabBookingSystem.BookingService.DTO.BookingRequestDTO;
import com.CabBookingSystem.BookingService.DTO.BookingResponseDTO;
import com.CabBookingSystem.BookingService.Entity.Booking;
import com.CabBookingSystem.BookingService.Event.BookingEvent;
import com.CabBookingSystem.BookingService.Repo.BookingRepository;
import com.CabBookingSystem.BookingService.Services.BookingServices;
import com.CabBookingSystem.BookingService.Util.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingServices {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String,BookingEvent> kafkaTemplate;

    @Autowired
    private BookingRepository bookingRepository;

    private static final String USER_SERVICE_URL = "http://user-service/user/{userId}";

    private  static final String  CAB_SERVICE_URL = "http://cab-service/api/cab/available";


    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        String userId = fetchUserId();
        String cabId = fetchAvailableCab();


        Booking booking = Booking.builder()
                .userId(userId)
                .cabId(cabId)
                .bookingTime(LocalDateTime.now())
                .status(Status.REQUESTED)
                .pickupLocation(request.getPickupLocation())
                .dropoffLocation(request.getDropoffLocation())
                .build();
        bookingRepository.save(booking);
        BookingEvent event = new BookingEvent(booking.getBookingId(),"REQUESTED",
                booking.getPickupLocation(),
                booking.getDropoffLocation());
        kafkaTemplate.send("ride-requested", event);
        return convertToResponseDto(booking);
    }

    @Override
    public BookingResponseDTO getBookingById(String bookingId) {
       Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new RuntimeException());
       return convertToResponseDto(booking);
    }

    @Override
    public List<BookingResponseDTO> getAllBookings() {
        List<Booking> booking = bookingRepository.findAll();
        booking.stream().collect(Collectors.toList());
        return booking.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseDTO> getBookingsByUserId(String userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(Status.CANCELLED);
        bookingRepository.save(booking);

        BookingEvent event = new BookingEvent(booking.getBookingId(), "CANCELLED",
                booking.getPickupLocation(),
                booking.getDropoffLocation());
        kafkaTemplate.send("ride-cancelled", event);
    }

    private String fetchUserId() {
        try {
            return restTemplate.getForObject(USER_SERVICE_URL, String.class);
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }
    }

    private String fetchAvailableCab() {
        try {
            List<String> availableCabs = restTemplate.getForObject(CAB_SERVICE_URL, List.class);
            if (availableCabs == null || availableCabs.isEmpty()) {
                throw new RuntimeException("No available cabs");
            }
            return availableCabs.get(0);
        } catch (Exception e) {
            throw new RuntimeException("No available cabs");
        }
    }


    private BookingResponseDTO convertToResponseDto(Booking booking) {
        return new BookingResponseDTO(
                booking.getBookingId(),
                booking.getStatus().name(),
                booking.getBookingTime(),
                booking.getPickupLocation(),
                booking.getDropoffLocation()
        );
    }

}