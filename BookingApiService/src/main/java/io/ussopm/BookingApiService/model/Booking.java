package io.ussopm.BookingApiService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(schema = "barbershop", name = "t_booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int id;
    @Column(name = "c_booking_date")
    private LocalDate bookingDate;
    @Column(name = "c_start_time")
    private LocalTime startTime;
    @Column(name = "c_end_time")
    private LocalTime endTime;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "barber_id", referencedColumnName = "barber_id")
    private Barber barber;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private ServiceItem service;
}
