package io.ussopm.BookingApiService.service;

import io.ussopm.BookingApiService.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);


    Optional<Customer> findByName(String customerName);
}
