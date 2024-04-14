package io.ussopm.BookingApiService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(schema = "barbershop", name = "t_barber")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barber_id")
    private Integer id;

    @Column(name = "c_barber_name")
    @NotNull(message = "Name have to be not null")
    @Size(min = 3, max = 40, message = "Name size min = 3, max 40")
    private String name;

    @Column(name = "c_email")
    @NotNull(message = "Email have to be not null")
    @Email
    private String email;

    @Column(name = "c_phone_number")
    @NotNull(message = "Phone number have to be not null")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "t_service_provider",
    joinColumns = @JoinColumn(name = "barber_id"),
    inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<ServiceItem> services;

    @OneToMany(mappedBy = "barber")
    private List<Booking> bookings;
}
