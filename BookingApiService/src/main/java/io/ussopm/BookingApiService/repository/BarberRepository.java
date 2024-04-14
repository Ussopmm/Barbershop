package io.ussopm.BookingApiService.repository;


import io.ussopm.BookingApiService.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<Barber, Integer> {

}
