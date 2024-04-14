package io.ussopm.BookingApiService.service.impl;

import io.ussopm.BookingApiService.model.Barber;
import io.ussopm.BookingApiService.model.Booking;
import io.ussopm.BookingApiService.model.Customer;
import io.ussopm.BookingApiService.model.ServiceItem;
import io.ussopm.BookingApiService.repository.BookingRepository;
import io.ussopm.BookingApiService.service.BarberService;
import io.ussopm.BookingApiService.service.BookingService;
import io.ussopm.BookingApiService.service.CustomerService;
import io.ussopm.BookingApiService.service.ServiceItemService;
import io.ussopm.BookingApiService.view.BookingView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BarberService barberService;
    private final CustomerService customerService;
    private final ServiceItemService serviceItem;
    @Value("${booking.time.openingTime}")
    private String openingHour;
    @Value("${booking.time.closingTime}")
    private String closeHour;
    @Value("${booking.time.duration}")
    private String duration;
    @Override
    public List<LocalTime> getAvailableTimeList(String dateTimeString, int barberId) {
        List<LocalTime> availableTimeList = new ArrayList<>();
//        LocalDate dateTime = dateTimeString == null || "" ? LocalDate.now() : LocalDate.parse(dateTimeString);
        LocalDate dateTime;
        if (dateTimeString == null || dateTimeString.isEmpty()) {
            dateTime = LocalDate.now();
        } else {
            dateTime = LocalDate.parse(dateTimeString);
        }
        LocalTime startTime = LocalTime.parse(openingHour);
        LocalTime endTime = LocalTime.parse(closeHour);
        long durationTime = Long.parseLong(duration);

        LocalTime tempTime = startTime;
        while (endTime.compareTo(tempTime) != 0) {
            List<Booking> bookings = bookingRepository.findByBookingDateAndStartTimeAndBarberId(dateTime, tempTime, barberId);
            if (bookings.isEmpty())
                availableTimeList.add(tempTime);
            tempTime = tempTime.plusMinutes(durationTime);
        }

        return availableTimeList;
    }

    @Override
    public void save(BookingView bookingView) {
        Customer customer = Customer.builder()
                .name(bookingView.getCustomerName())
                .email(bookingView.getCustomerEmail())
                .phoneNumber(bookingView.getPhoneNumber())
                .build();
        Optional<Customer> existingCustomer = customerService.findByName(bookingView.getCustomerName());
        if (existingCustomer.isPresent()) {
            customer = existingCustomer.get();
        } else {
            customer = customerService.save(customer);
        }
        Optional<ServiceItem> service = serviceItem.getServiceById(bookingView.getServiceId());
        Optional<Barber> barber = barberService.getBarberById(bookingView.getBarberId());

        Booking booking = Booking.builder()
                .bookingDate(bookingView.getBookingDate())
                .startTime(bookingView.getStartTime())
                .endTime(bookingView.getStartTime().plusMinutes(30))
                .comments(bookingView.getComments())
                .barber(barber.get())
                .customer(customer)
                .service(service.get())
                .build();
        bookingRepository.save(booking);
    }
}
