package io.ussopm.AdminService.controller;

import io.ussopm.AdminService.dto.CustomerDTO;
import io.ussopm.AdminService.dto.ServiceItemDTO;
import io.ussopm.AdminService.exception.AlreadyExistsException;
import io.ussopm.AdminService.model.Customer;
import io.ussopm.AdminService.model.ServiceItem;
import io.ussopm.AdminService.service.CustomerService;
import io.ussopm.AdminService.service.ServiceItemService;
import io.ussopm.AdminService.util.GeneralExceptionsHandler;
import io.ussopm.AdminService.util.MappingClasses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("admin/api/services")
@RequiredArgsConstructor
public class ServiceRestController {

    private final ServiceItemService serviceItemService;
    private final GeneralExceptionsHandler handler;
    private final MappingClasses mapper;

    @GetMapping()
    public List<ServiceItem> getAllServices() {
        return serviceItemService.getAllServices();
    }

    @GetMapping("/{id}")
    public Object getServiceById(@PathVariable("id") int serviceId) {
        try {
            return serviceItemService.getServiceById(serviceId);
        } catch (NoSuchElementException ex) {
            return handler.handleNoSuchElementException(ex, "Service");
        }
    }
    @PostMapping("/new")
    public ResponseEntity<? extends Object> creatingNewService(@RequestBody @Valid ServiceItemDTO serviceDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            try {
                return serviceItemService.save(mapper.convertToModel(serviceDTO, ServiceItem.class));
            } catch (AlreadyExistsException ex) {
                return handler.handleAlreadyExistsException(ex, "Service");
            }
        }
    }
    @PatchMapping("/{id}/update")
    private ResponseEntity<? extends Object> updatingService(@PathVariable("id") int serviceId, @RequestBody @Valid ServiceItemDTO serviceDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            try {
                return serviceItemService.updateServiceById(serviceId,mapper.convertToModel(serviceDTO, ServiceItem.class));
            } catch (NoSuchElementException ex) {
                return handler.handleNoSuchElementException(ex, "Service");
            }
        }
    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity<? extends Object> deletingService(@PathVariable("id") int serviceId) {
        try {
            return serviceItemService.deleteServiceById(serviceId);
        } catch (NoSuchElementException ex) {
            return handler.handleNoSuchElementException(ex, "Service");
        }
    }
}
