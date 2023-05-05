package com.example.delivery_control.service;

import com.example.delivery_control.dto.RestaurantDto;
import com.example.delivery_control.models.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDto> findAllRestaurant();
    Restaurant saveRestaurant(RestaurantDto restaurantDto);

    RestaurantDto findRestaurantById(long restaurant_id);

    void updateRestaurant(RestaurantDto restaurantDto);

    void delete(Long restaurantId);
    List<RestaurantDto> searchRestaurant(String query);
    void countAvgRatingsAndAmount(RestaurantDto restaurantDto);
    double countAvgForChart(RestaurantDto restaurantDto);

    List<List<Object>> restaurantChartData();
}
