package com.CabBookingSystem.BookingService.Services.impl;

import com.CabBookingSystem.BookingService.Services.FareService;
import org.springframework.stereotype.Service;

@Service
public class FareServiceImpl implements FareService {
    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 12.0;
    private static final double LONG_DISTANCE_DISCOUNT = 0.90;


    @Override
    public double calculateFare(double distance) {
        double fare = BASE_FARE + (distance * PER_KM_RATE);

        if (distance > 100) {
            fare *= LONG_DISTANCE_DISCOUNT;
        }
        return fare;
    }
}
