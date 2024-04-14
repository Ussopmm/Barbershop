package io.ussopm.BookingApiService.service.impl;

import io.ussopm.BookingApiService.model.Barber;
import io.ussopm.BookingApiService.model.ServiceItem;
import io.ussopm.BookingApiService.repository.BarberRepository;
import io.ussopm.BookingApiService.repository.ServiceRepository;
import io.ussopm.BookingApiService.service.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarberServiceImpl implements BarberService {

    private final BarberRepository barberRepository;
    private final ServiceRepository serviceRepository;
    @Override
    public List<Barber> getAllBarbersByServiceId(int serviceId) {
        ServiceItem serviceItem = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new NoSuchElementException("Service not found"));
        return serviceItem.getBarbers();
    }

    @Override
    public Optional<Barber> findById(int barberId) {
        return barberRepository.findById(barberId);
    }

    @Override
    public Optional<Barber> getBarberById(Integer barberId) {
        return barberRepository.findById(barberId);
    }

    @Override
    public boolean existBarberById(int id) {
       return barberRepository.existsById(id);
    }
}
