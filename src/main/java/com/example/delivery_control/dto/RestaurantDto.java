package com.example.delivery_control.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantDto {
    private int id;
    private String restaurant_name;
    private String restaurant_address;
    private String restaurant_phone;
}

