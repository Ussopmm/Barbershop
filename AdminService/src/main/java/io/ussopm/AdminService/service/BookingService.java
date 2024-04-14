package io.ussopm.AdminService.service;

import io.ussopm.AdminService.model.Booking;
import io.ussopm.AdminService.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();

    Booking getBookingById(int bookingId);

    ResponseEntity<HttpStatus> save(Booking booking);

    ResponseEntity<HttpStatus> updateBookingById(int bookingId, Booking booking);

    ResponseEntity<HttpStatus> deleteBookingById(int bookingId);
}
