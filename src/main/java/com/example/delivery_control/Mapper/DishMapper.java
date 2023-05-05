package com.example.delivery_control.Mapper;

import com.example.delivery_control.dto.DishDto;
import com.example.delivery_control.dto.ReviewDto;
import com.example.delivery_control.models.Dish;
import com.example.delivery_control.models.Review;

public class DishMapper {
    public static Dish mapToDish(DishDto dishDto ){
        return Dish.builder()
                .id(dishDto.getId())
                .name(dishDto.getName())
                .price(dishDto.getPrice())
                .description(dishDto.getDescription())
                .dish_imgurl(dishDto.getDish_imgurl())
                .restaurant(dishDto.getRestaurant())
                .build();
    }
    public static DishDto mapToDishDto(Dish dish ){
        return DishDto.builder()
                .id(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .description(dish.getDescription())
                .dish_imgurl(dish.getDish_imgurl())
                .restaurant(dish.getRestaurant())
                .build();
    }
}
