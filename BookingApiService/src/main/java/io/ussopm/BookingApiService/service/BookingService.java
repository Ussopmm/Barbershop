package io.ussopm.BookingApiService.service;

import io.ussopm.BookingApiService.view.BookingView;

import java.time.LocalTime;
import java.util.List;

public interface BookingService {
    List<LocalTime> getAvailableTimeList(String dateString, int barberId);

    void save(BookingView bookingView);
}
