package com.example.delivery_control.Repository;

import com.example.delivery_control.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT c from Restaurant c WHERE c.restaurant_name LIKE CONCAT('%', :query, '%')")
    List<Restaurant> searchRestaurant(String query);

    @Query("SELECT r FROM Restaurant r WHERE r.restaurant_type LIKE CONCAT('%', :type, '%')")
    List<Restaurant> searchRestaurantByType(String type);

}
