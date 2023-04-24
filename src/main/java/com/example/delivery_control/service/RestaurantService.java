package com.example.delivery_control.service;

import com.example.delivery_control.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDto> findAllRestaurant();
}
