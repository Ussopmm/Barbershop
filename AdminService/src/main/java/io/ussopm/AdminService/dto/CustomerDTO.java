package io.ussopm.AdminService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {


    @NotNull(message = "Name have to be not null")
    @Size(min = 3, max = 40, message = "Name size min = 3, max 40")
    private String name;

    @NotNull(message = "Email have to be not null")
    @Email
    private String email;

    @NotNull(message = "Phone number have to be not null")
    private String phoneNumber;
}
