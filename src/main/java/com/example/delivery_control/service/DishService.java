package com.example.delivery_control.service;

import com.example.delivery_control.dto.DishDto;
import com.example.delivery_control.dto.ReviewDto;

import java.util.List;

public interface DishService {
    void createDish(Long restaurant_id, DishDto dishDto);
    List<DishDto> findAllDishes();

    DishDto findByDishId(Long dish_id);
    List<DishDto> findDishByRestaurantId(int restaurant_id);

    void updateDish(DishDto dishDto);

    void deleteDish(long dishId);
}
