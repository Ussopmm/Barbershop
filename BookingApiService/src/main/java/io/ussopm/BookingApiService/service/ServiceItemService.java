package io.ussopm.BookingApiService.service;

import io.ussopm.BookingApiService.model.ServiceItem;

import java.util.List;
import java.util.Optional;

public interface ServiceItemService {
    List<ServiceItem> getAllServices();

    Optional<ServiceItem> getServiceById(Integer serviceId);

    boolean existsServiceById(int serviceId);
}
