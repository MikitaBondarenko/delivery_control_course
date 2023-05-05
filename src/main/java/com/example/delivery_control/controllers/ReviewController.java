package com.example.delivery_control.controllers;


import com.example.delivery_control.dto.RestaurantDto;
import com.example.delivery_control.dto.ReviewDto;
import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.models.Review;
import com.example.delivery_control.models.UserEntity;
import com.example.delivery_control.security.SecurityUtill;
import com.example.delivery_control.service.ReviewService;
import com.example.delivery_control.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {
    private ReviewService reviewService;
    private UserService userService;
    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }
    @GetMapping("/reviews/{restaurant_id}/new")
    public String createReviewForm(@PathVariable("restaurant_id") Long restaurant_id, Model model)
    {
        Review review = new Review();
        model.addAttribute("restaurant_id", restaurant_id);
        model.addAttribute("review", review);
        return "review-create";
    }

    @PostMapping("/reviews/{restaurant_id}")
    public String createReview(@PathVariable("restaurant_id") Long restaurant_id,
                               @Valid
                               @ModelAttribute("review")ReviewDto reviewDto,
                               BindingResult result, Model model)
    {
        if(result.hasErrors()){
            model.addAttribute("review", reviewDto);
            return "review-create";
        }
        reviewService.createReview(restaurant_id, reviewDto);
        return "redirect:/restaurants/" + restaurant_id;
    }

    @GetMapping("/reviews")
    public  String reviewList(Model model){
        List<ReviewDto> reviewDtoList = reviewService.findAllReviews();
        model.addAttribute("reviews", reviewDtoList);
        return "review-list";
    }

    @GetMapping("/reviews/{review_id}")
    public String viewReview(@PathVariable("review_id") Long review_id, Model model){
        UserDto user = new UserDto();
        ReviewDto reviewDto =  reviewService.findByReviewId(review_id);
        String username = SecurityUtill.getSessionUser();
        if(username != null){
            user=userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("review", reviewDto);
        return "review-detail";
    }

    @GetMapping("/reviews/{review_id}/edit")
    public String editReview(@PathVariable("review_id") long review_id, Model  model){
        ReviewDto reviewDto = reviewService.findByReviewId(review_id);
        model.addAttribute("review" , reviewDto);
        return "review-edit";
    }

    @PostMapping("/reviews/{review_id}/edit")
    public String updateReview(@PathVariable("review_id") long review_id,
                                   @Valid
                                   @ModelAttribute("review") ReviewDto reviewDto,
                                   BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("review", reviewDto);
            return "review-edit";
        }
        ReviewDto reviewDto1 = reviewService.findByReviewId(review_id);
        reviewDto.setId(review_id);
        reviewDto.setRestaurant(reviewDto1.getRestaurant());
        reviewService.updateReview(reviewDto);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/{review_id}/delete")
    public  String deleteReview(@PathVariable("review_id") long review_id,
                                @ModelAttribute("review") ReviewDto reviewDto){
        reviewDto = reviewService.findByReviewId(review_id);
        reviewService.deleteReview(review_id);
        return  "redirect:/restaurants/" + reviewDto.getRestaurant().getId();
    }
}

