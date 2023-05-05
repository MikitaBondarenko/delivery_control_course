package com.example.delivery_control.Mapper;

import com.example.delivery_control.dto.ReviewDto;
import com.example.delivery_control.models.Review;

public class ReviewMapper {
    public static Review mapToReview(ReviewDto reviewDto ){
        return Review.builder()
                .id(reviewDto.getId())
                .comment(reviewDto.getComment())
                .advantages(reviewDto.getAdvantages())
                .disadvantages(reviewDto.getDisadvantages())
                .review_imgurl(reviewDto.getReview_imgurl())
                .createdOn(reviewDto.getCreatedOn())
                .updatedOn(reviewDto.getUpdatedOn())
                .rating(reviewDto.getRating())
                .restaurant(reviewDto.getRestaurant())
                .created_by(reviewDto.getCreated_by())
                .build();
    }

    public static ReviewDto mapToReviewDto(Review review ){
        return ReviewDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .advantages(review.getAdvantages())
                .disadvantages(review.getDisadvantages())
                .review_imgurl(review.getReview_imgurl())
                .createdOn(review.getCreatedOn())
                .updatedOn(review.getUpdatedOn())
                .rating(review.getRating())
                .restaurant(review.getRestaurant())
                .created_by(review.getCreated_by())
                .build();
    }
}
