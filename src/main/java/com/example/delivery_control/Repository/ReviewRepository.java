package com.example.delivery_control.Repository;

import com.example.delivery_control.dto.ReviewDto;
import com.example.delivery_control.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByRestaurantId(int restaurant_id);
}
