package io.ussopm.AdminService.repository;


import io.ussopm.AdminService.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarberRepository extends JpaRepository<Barber, Integer> {

    Optional<Barber> findBarberByName(String barberName);
}
