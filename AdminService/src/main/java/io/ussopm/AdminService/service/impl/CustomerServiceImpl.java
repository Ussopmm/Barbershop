package io.ussopm.AdminService.service.impl;

import io.ussopm.AdminService.exception.AlreadyExistsException;
import io.ussopm.AdminService.model.Customer;
import io.ussopm.AdminService.repository.CustomerRepository;
import io.ussopm.AdminService.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public ResponseEntity<HttpStatus> save(Customer customer) {
        if (customerRepository.findCustomerByName(customer.getName()).isPresent()) {
            throw new AlreadyExistsException();
        } else {
            customerRepository.save(customer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> updateCustomerById(int customerId, Customer customerForUpdate) {
        this.customerRepository.findById(customerId).ifPresentOrElse(customer -> {
            customer.setName(customerForUpdate.getName());
            customer.setEmail(customerForUpdate.getEmail());
            customer.setPhoneNumber(customerForUpdate.getPhoneNumber());
        },() -> {
            throw new NoSuchElementException();
        } );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteCustomerById(int customerId) {
        if (!this.customerRepository.findById(customerId).isPresent()) {
            throw new NoSuchElementException();
        } else {
            this.customerRepository.deleteById(customerId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
