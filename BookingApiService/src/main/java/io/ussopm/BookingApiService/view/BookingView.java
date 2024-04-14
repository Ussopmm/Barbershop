package io.ussopm.BookingApiService.view;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingView {

    private int bookingId;

    private LocalDate bookingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String comments;

    @NotBlank
    @NotNull(message = "Name have to be not null")
    @Size(min = 3, max = 40, message = "Name size min = 3, max 40")
    private String customerName;
    @NotBlank
    @NotNull(message = "Email have to be not null")
    @Email
    private String customerEmail;
    @NotBlank
    @NotNull(message = "Phone number have to be not null")
    private String phoneNumber;
    private Integer barberId;
    private Integer serviceId;
}

