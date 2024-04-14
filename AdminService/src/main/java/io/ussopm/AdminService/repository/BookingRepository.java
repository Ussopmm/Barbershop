package io.ussopm.AdminService.repository;




import io.ussopm.AdminService.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByBookingDateAndStartTimeAndBarberId(LocalDate dateTime,LocalTime tempTime,int barberId);

}
