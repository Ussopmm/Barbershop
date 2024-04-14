package io.ussopm.AdminService.controller;

import io.ussopm.AdminService.dto.BookingDTO;
import io.ussopm.AdminService.exception.AlreadyExistsException;
import io.ussopm.AdminService.model.Booking;
import io.ussopm.AdminService.service.BookingService;
import io.ussopm.AdminService.util.GeneralExceptionsHandler;
import io.ussopm.AdminService.util.MappingClasses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("admin/api/bookings")
@RequiredArgsConstructor
public class BookingRestController {

    private final BookingService bookingService;
    private final GeneralExceptionsHandler handler;
    private final MappingClasses mapper;
    @GetMapping()
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings().stream().map(booking -> mapper.convertToDTO(booking, BookingDTO.class)).toList();
    }

    @GetMapping("/{id}")
    public Object getBookingById(@PathVariable("id") int bookingId) {
        try {
            return mapper.convertToDTO(bookingService.getBookingById(bookingId), BookingDTO.class);
        } catch (NoSuchElementException ex) {
            return handler.handleNoSuchElementException(ex, "Customer");
        }
    }
    @PostMapping("/new")
    public ResponseEntity<? extends Object> creatingNewBooking(@RequestBody @Valid BookingDTO bookingDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            try {
                return bookingService.save(mapper.convertToModel(bookingDTO, Booking.class));
            } catch (AlreadyExistsException ex) {
                return handler.handleAlreadyExistsException(ex, "Booking");
            }
        }
    }
    @PatchMapping("/{id}/update")
    private ResponseEntity<? extends Object> updatingBooking(@PathVariable("id") int bookingId, @RequestBody @Valid BookingDTO bookingDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            try {
                return bookingService.updateBookingById(bookingId,mapper.convertToModel(bookingDTO, Booking.class));
            } catch (NoSuchElementException ex) {
                return handler.handleNoSuchElementException(ex, "Booking");
            }
        }
    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity<? extends Object> deletingCustomer(@PathVariable("id") int bookingId) {
        try {
            return bookingService.deleteBookingById(bookingId);
        } catch (NoSuchElementException ex) {
            return handler.handleNoSuchElementException(ex, "Booking");
        }
    }
}
