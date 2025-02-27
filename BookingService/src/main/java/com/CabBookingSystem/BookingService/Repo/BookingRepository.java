package com.CabBookingSystem.BookingService.Repo;

import com.CabBookingSystem.BookingService.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,String> {

    List<Booking> findByUserId(String userId);
}
