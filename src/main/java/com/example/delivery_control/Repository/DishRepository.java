package com.example.delivery_control.Repository;

import com.example.delivery_control.models.Dish;
import com.example.delivery_control.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAllByRestaurantId(int restaurant_id);
}
