package com.example.delivery_control.service;

import com.example.delivery_control.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void createReview(Long restaurant_id, ReviewDto reviewDto);
    List<ReviewDto> findAllReviews();

    ReviewDto findByReviewId(Long review_id);
    List<ReviewDto> findReviewByRestaurantId(int restaurant_id);

    void updateReview(ReviewDto reviewDto);

    void deleteReview(long reviewId);
}
