package com.example.delivery_control.service;

public interface OrderService {
    void addDishToCart(String username, Long dishId, int dishquantity);
}
