package io.ussopm.AdminService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(schema = "barbershop", name = "t_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;

    @Column(name = "c_customer_name")
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

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;
}
