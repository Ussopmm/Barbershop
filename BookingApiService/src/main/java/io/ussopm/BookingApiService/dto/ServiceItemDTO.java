package io.ussopm.BookingApiService.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItemDTO {

    private int id;
    @NotNull(message = "Name have to be not null")
    @Size(min = 3, max = 100, message = "Name size min = 3, max 40")
    private String name;

    private int duration;
    private int price;
    private String description;
}
