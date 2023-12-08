package com.example.delivery_control.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private  String username;
    private String password;
    private String email;
    private Boolean userRole;
    private String surname;
    private String firstname;
    private String address;
    private String avatarImg;
    private String phone;

}
