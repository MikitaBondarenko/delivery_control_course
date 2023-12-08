package com.example.delivery_control.service;

import com.example.delivery_control.dto.RegistrationDto;
import com.example.delivery_control.dto.RestaurantDto;
import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.models.UserEntity;
import jakarta.servlet.Registration;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserDto findByEmail(String email);

    UserDto findByUsername(String username);

    void updateUser(UserDto userDto);
}
