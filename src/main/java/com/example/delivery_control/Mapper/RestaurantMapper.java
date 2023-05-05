package com.example.delivery_control.Mapper;

import com.example.delivery_control.dto.RestaurantDto;
import com.example.delivery_control.models.Restaurant;

import java.util.stream.Collectors;

import static com.example.delivery_control.Mapper.ReviewMapper.mapToReviewDto;

public class RestaurantMapper {
    public static Restaurant mapToRestaurant(RestaurantDto restaurant) {
        Restaurant restaurantDto = Restaurant.builder()
                .id(restaurant.getId())
                .restaurant_name(restaurant.getRestaurant_name())
                .restaurant_address(restaurant.getRestaurant_address())
                .restaurant_imgurl(restaurant.getRestaurant_imgurl())
                .restaurant_phone(restaurant.getRestaurant_phone())
                .created_by(restaurant.getCreated_by())
                .restaurant_site(restaurant.getRestaurant_site())
                .build();
        return restaurantDto;
    }

    public static RestaurantDto mapToRestaurantDto(Restaurant restaurant){
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .restaurant_name(restaurant.getRestaurant_name())
                .restaurant_address(restaurant.getRestaurant_address())
                .restaurant_phone(restaurant.getRestaurant_phone())
                .restaurant_imgurl(restaurant.getRestaurant_imgurl())
                .restaurant_site(restaurant.getRestaurant_site())
                .created_by(restaurant.getCreated_by())
                .reviewDtoList(restaurant.getReviewList().stream().map(ReviewMapper::mapToReviewDto).collect(Collectors.toList()))
                .dishDtoList(restaurant.getDishList().stream().map(DishMapper::mapToDishDto).collect(Collectors.toList()))
                .build();
    }
}
