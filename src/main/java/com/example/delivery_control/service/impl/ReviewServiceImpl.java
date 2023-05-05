package com.example.delivery_control.service.impl;

import com.example.delivery_control.Mapper.RestaurantMapper;
import com.example.delivery_control.Mapper.ReviewMapper;
import com.example.delivery_control.Repository.RestaurantRepository;
import com.example.delivery_control.Repository.ReviewRepository;
import com.example.delivery_control.Repository.UserRepository;
import com.example.delivery_control.dto.ReviewDto;
import com.example.delivery_control.models.Restaurant;
import com.example.delivery_control.models.Review;
import com.example.delivery_control.models.UserEntity;
import com.example.delivery_control.security.SecurityUtill;
import com.example.delivery_control.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.delivery_control.Mapper.ReviewMapper.mapToReview;
import static com.example.delivery_control.Mapper.ReviewMapper.mapToReviewDto;
@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Override
    public void createReview(Long restaurant_id, ReviewDto reviewDto) {
        String username = SecurityUtill.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();
        Review review = mapToReview(reviewDto);
        review.setRestaurant(restaurant);
        review.setCreated_by(user);
        reviewRepository.save(review);
    }

    @Override
    public List<ReviewDto> findAllReviews() {
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList.stream().map(review -> mapToReviewDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto findByReviewId(Long review_id) {
        Review review = reviewRepository.findById(review_id).get();
        return mapToReviewDto(review);
    }

    @Override
    public List<ReviewDto> findReviewByRestaurantId(int restaurant_id) {
        var reviewList = reviewRepository.findAllByRestaurantId(restaurant_id);
        return reviewList.stream().map(ReviewMapper::mapToReviewDto).collect(Collectors.toList());
    }

    @Override
    public void updateReview(ReviewDto reviewDto) {
        String username = SecurityUtill.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Review review = mapToReview(reviewDto);
        review.setCreated_by(user);
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(long review_id) {
        reviewRepository.deleteById(review_id);
    }
}
