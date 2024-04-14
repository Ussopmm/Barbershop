package io.ussopm.AdminService.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceItemDTO {

    @NotNull(message = "Name have to be not null")
    @Size(min = 3, max = 100, message = "Name size min = 3, max 40")
    private String name;
    private int duration;
    private int price;
    private String description;
}
