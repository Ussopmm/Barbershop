package io.ussopm.AdminService.service.impl;

import io.ussopm.AdminService.dto.BookingDTO;
import io.ussopm.AdminService.exception.AlreadyExistsException;
import io.ussopm.AdminService.model.Booking;
import io.ussopm.AdminService.model.Customer;
import io.ussopm.AdminService.repository.BookingRepository;
import io.ussopm.AdminService.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    @Override
    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(int bookingId) {
        return this.bookingRepository.findById(bookingId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public ResponseEntity<HttpStatus> save(Booking booking) {
        this.bookingRepository.save(booking);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<HttpStatus> updateBookingById(int bookingId, Booking bookingForUpdate) {
        this.bookingRepository.findById(bookingId).ifPresentOrElse(booking -> {
            booking.setBookingDate(bookingForUpdate.getBookingDate());
            booking.setStartTime(bookingForUpdate.getStartTime());
            booking.setEndTime(bookingForUpdate.getEndTime());
            booking.setComments(bookingForUpdate.getComments());
        }, () -> {
            throw new NoSuchElementException();
        });

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity<HttpStatus> deleteBookingById(int bookingId) {
        if (!this.bookingRepository.findById(bookingId).isPresent()) {
            throw new NoSuchElementException();
        } else {
            this.bookingRepository.deleteById(bookingId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
