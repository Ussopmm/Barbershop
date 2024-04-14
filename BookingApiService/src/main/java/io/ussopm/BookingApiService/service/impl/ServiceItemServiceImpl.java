package io.ussopm.BookingApiService.service.impl;

import io.ussopm.BookingApiService.model.ServiceItem;
import io.ussopm.BookingApiService.repository.ServiceRepository;
import io.ussopm.BookingApiService.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemService {

    private final ServiceRepository serviceRepository;

    @Override
    public List<ServiceItem> getAllServices() {
        return this.serviceRepository.findAll();
    }

    @Override
    public Optional<ServiceItem> getServiceById(Integer serviceId) {
        return serviceRepository.findById(serviceId);
    }

    @Override
    public boolean existsServiceById(int serviceId) {
        return serviceRepository.existsById(serviceId);
    }
}
