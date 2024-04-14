package io.ussopm.BookingApiService.service;

import io.ussopm.BookingApiService.model.Barber;

import java.util.List;
import java.util.Optional;

public interface BarberService {

    List<Barber> getAllBarbersByServiceId(int serviceId);

    Optional<Barber> findById(int barberId);

    Optional<Barber> getBarberById(Integer barberId);

    boolean existBarberById(int id);
}
