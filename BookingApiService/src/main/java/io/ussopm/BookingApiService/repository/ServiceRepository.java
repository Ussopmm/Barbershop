package io.ussopm.BookingApiService.repository;


import io.ussopm.BookingApiService.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceItem, Integer> {
}
