package com.CabBookingSystem.Notification.service.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "ride-requested", groupId = "notification-group")
    public void handleRideRequest(String message) {
        System.out.println("Notification: Ride Requested - " + message);

    }

    @KafkaListener(topics = "ride-cancelled", groupId = "notification-group")
    public void handleRideCancellation(String message) {
        System.out.println("Notification: Ride Cancelled - " + message);

    }

    @KafkaListener(topics = "user-updated", groupId = "notification-group")
    public void handleUserUpdate(String message) {
        System.out.println("Notification: User Updated - " + message);

    }

    @KafkaListener(topics = "user-delete", groupId = "notification-group")
    public void handleUserDeletion(String message) {
        System.out.println("Notification: User Deleted - " + message);
    }
}
