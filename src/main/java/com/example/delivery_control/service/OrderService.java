package com.example.delivery_control.service;

import com.example.delivery_control.dto.DishDto;

import java.util.List;

public interface OrderService {
    void addDishToCart(String username, Long dishId, int dishquantity);
    List<DishDto> findAllCartItemDishes(String username);
}
