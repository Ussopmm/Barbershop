package io.ussopm.AdminService.service;

import io.ussopm.AdminService.model.Barber;
import io.ussopm.AdminService.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BarberService {


    List<Barber> getAllBarbers();

    Barber getBarberById(int barberId);

    ResponseEntity<HttpStatus> save(Barber barber);

    ResponseEntity<HttpStatus> updateBarberById(int barberId, Barber barber);

    ResponseEntity<HttpStatus> deleteBarberById(int barberId);
}
