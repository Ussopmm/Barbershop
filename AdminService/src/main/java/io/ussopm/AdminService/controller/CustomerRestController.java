package io.ussopm.AdminService.controller;

import io.ussopm.AdminService.dto.CustomerDTO;
import io.ussopm.AdminService.exception.AlreadyExistsException;
import io.ussopm.AdminService.model.Customer;
import io.ussopm.AdminService.service.CustomerService;
import io.ussopm.AdminService.util.GeneralExceptionsHandler;
import io.ussopm.AdminService.util.MappingClasses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin/api/customers")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;
    private final GeneralExceptionsHandler handler;
    private final MappingClasses mapper;
    @GetMapping()
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Object getCustomerById(@PathVariable("id") int customerId) {
        try {
            return customerService.getCustomerById(customerId);
        } catch (NoSuchElementException ex) {
            return handler.handleNoSuchElementException(ex, "Customer");
        }
    }
    @PostMapping("/new")
    public ResponseEntity<? extends Object> creatingNewCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            try {
                return customerService.save(mapper.convertToModel(customerDTO, Customer.class));
            } catch (AlreadyExistsException ex) {
                return handler.handleAlreadyExistsException(ex, "Customer");
            }
        }
    }
    @PatchMapping("/{id}/update")
    private ResponseEntity<? extends Object> updatingCustomer(@PathVariable("id") int customerId, @RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            try {
                return customerService.updateCustomerById(customerId,mapper.convertToModel(customerDTO, Customer.class));
            } catch (NoSuchElementException ex) {
                return handler.handleNoSuchElementException(ex, "Customer");
            }
        }
    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity<? extends Object> deletingCustomer(@PathVariable("id") int customerId) {
        try {
            return customerService.deleteCustomerById(customerId);
        } catch (NoSuchElementException ex) {
            return handler.handleNoSuchElementException(ex, "Customer");
        }
    }
}
