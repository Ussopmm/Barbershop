package io.ussopm.AdminService.service.impl;

import io.ussopm.AdminService.exception.AlreadyExistsException;
import io.ussopm.AdminService.model.ServiceItem;
import io.ussopm.AdminService.repository.ServiceRepository;
import io.ussopm.AdminService.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemService {
    private final ServiceRepository serviceRepository;
    @Override
    public List<ServiceItem> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Object getServiceById(int serviceId) {
        return serviceRepository.findById(serviceId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public ResponseEntity<? extends Object> save(ServiceItem serviceItem) {
        if (serviceRepository.findServiceItemByName(serviceItem.getName()).isPresent()) {
            throw new AlreadyExistsException();
        } else {
            serviceRepository.save(serviceItem);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<? extends Object> updateServiceById(int serviceId, ServiceItem serviceItem) {
        this.serviceRepository.findById(serviceId).ifPresentOrElse(service -> {
            service.setName(service.getName());
            service.setPrice(service.getPrice());
            service.setDuration(service.getDuration());
            service.setDescription(service.getDescription());
        },() -> {
            throw new NoSuchElementException();
        } );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<? extends Object> deleteServiceById(int serviceId) {
        if (!this.serviceRepository.findById(serviceId).isPresent()) {
            throw new NoSuchElementException();
        } else {
            this.serviceRepository.deleteById(serviceId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
