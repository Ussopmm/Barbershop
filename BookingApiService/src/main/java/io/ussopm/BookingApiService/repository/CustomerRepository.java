package io.ussopm.BookingApiService.repository;


import io.ussopm.BookingApiService.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    Optional<Customer> findCustomerByName(String name);
}
