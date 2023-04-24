package com.example.delivery_control.service.impl;

import com.example.delivery_control.Repository.RestaurantRepository;
import com.example.delivery_control.dto.RestaurantDto;
import com.example.delivery_control.models.Restaurant;
import com.example.delivery_control.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
//r

    @Override
    public List<RestaurantDto> findAllRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(this::mapToRestaurantDto).collect(Collectors.toList());
    }

    private RestaurantDto mapToRestaurantDto(Restaurant restaurant){
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .restaurant_name(restaurant.getRestaurant_name())
                .restaurant_address(restaurant.getRestaurant_address())
                .restaurant_phone(restaurant.getRestaurant_phone())
                .build();
    }
}
