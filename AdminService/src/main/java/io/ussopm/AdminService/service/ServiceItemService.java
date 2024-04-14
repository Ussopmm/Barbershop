package io.ussopm.AdminService.service;

import io.ussopm.AdminService.model.ServiceItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceItemService {
    List<ServiceItem> getAllServices();

    Object getServiceById(int serviceId);

    ResponseEntity<? extends Object> save(ServiceItem serviceItem);

    ResponseEntity<? extends Object> updateServiceById(int serviceId, ServiceItem serviceItem);

    ResponseEntity<? extends Object> deleteServiceById(int serviceId);
}
