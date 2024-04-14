package io.ussopm.AdminService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(schema = "barbershop", name = "t_service")
public class ServiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int id;

    @Column(name = "c_service_name")
    @NotNull(message = "Name have to be not null")
    @Size(min = 3, max = 100, message = "Name size min = 3, max 40")
    private String name;

    @Column(name = "c_duration")
    private int duration;
    @Column(name = "c_price")
    private int price;
    @Column(name = "c_description")
    private String description;

    @ManyToMany(mappedBy = "services")
    private List<Barber> barbers;

    @OneToMany(mappedBy = "service")
    private List<Booking> bookings;
}
