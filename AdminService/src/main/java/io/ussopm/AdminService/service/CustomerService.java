package io.ussopm.AdminService.service;

import io.ussopm.AdminService.dto.CustomerDTO;
import io.ussopm.AdminService.exception.AlreadyExistsException;
import io.ussopm.AdminService.model.Customer;
import io.ussopm.AdminService.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;



public interface CustomerService {


    List<Customer> getAllCustomers();

    Customer getCustomerById(int customerId);

    ResponseEntity<HttpStatus> save(Customer customer);

    ResponseEntity<HttpStatus> updateCustomerById(int customerId, Customer customer);

    ResponseEntity<HttpStatus> deleteCustomerById(int customerId);
}
