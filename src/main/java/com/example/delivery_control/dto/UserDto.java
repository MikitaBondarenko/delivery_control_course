package com.example.delivery_control.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserDto {
    private int id;
    private String user_name;
    private String user_surname;
    private String user_address;
    private String user_phone;
    private int user_role;
}
