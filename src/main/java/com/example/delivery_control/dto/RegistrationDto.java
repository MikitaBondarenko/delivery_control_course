package com.example.delivery_control.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Введите данные!")
    private String username;
    @NotEmpty(message = "Введите данные!")
    private String password;
    @NotEmpty(message = "Введите данные!")
    private String email;
}
