package io.ussopm.BookingApiService.service.impl;

import io.ussopm.BookingApiService.model.Customer;
import io.ussopm.BookingApiService.repository.CustomerRepository;
import io.ussopm.BookingApiService.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }



    @Override
    public Optional<Customer> findByName(String customerName) {
        return customerRepository.findCustomerByName(customerName);
    }
}
