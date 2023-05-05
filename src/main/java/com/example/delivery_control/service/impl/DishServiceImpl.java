package com.example.delivery_control.service.impl;

import com.example.delivery_control.Mapper.DishMapper;
import com.example.delivery_control.Mapper.ReviewMapper;
import com.example.delivery_control.Repository.DishRepository;
import com.example.delivery_control.Repository.RestaurantRepository;
import com.example.delivery_control.Repository.ReviewRepository;
import com.example.delivery_control.Repository.UserRepository;
import com.example.delivery_control.dto.DishDto;
import com.example.delivery_control.models.Dish;
import com.example.delivery_control.models.Restaurant;
import com.example.delivery_control.models.Review;
import com.example.delivery_control.models.UserEntity;
import com.example.delivery_control.security.SecurityUtill;
import com.example.delivery_control.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.delivery_control.Mapper.DishMapper.mapToDish;
import static com.example.delivery_control.Mapper.DishMapper.mapToDishDto;
import static com.example.delivery_control.Mapper.ReviewMapper.mapToReview;
import static com.example.delivery_control.Mapper.ReviewMapper.mapToReviewDto;

@AllArgsConstructor
@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Override
    public void createDish(Long restaurant_id, DishDto dishDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();
        Dish dish = mapToDish(dishDto);
        dish.setRestaurant(restaurant);
        dishRepository.save(dish);
    }

    @Override
    public List<DishDto> findAllDishes() {
        List<Dish> dishList = dishRepository.findAll();
        return dishList.stream().map(DishMapper::mapToDishDto).collect(Collectors.toList());
    }

    @Override
    public DishDto findByDishId(Long dish_id) {
        Dish dish = dishRepository.findById(dish_id).get();
        return mapToDishDto(dish);
    }

    @Override
    public List<DishDto> findDishByRestaurantId(int restaurant_id) {
        var dishList = dishRepository.findAllByRestaurantId(restaurant_id);
        return dishList.stream().map(DishMapper::mapToDishDto).collect(Collectors.toList());
    }

    @Override
    public void updateDish(DishDto dishDto) {
        Dish dish = mapToDish(dishDto);
        dishRepository.save(dish);
    }

    @Override
    public void deleteDish(long dishId) {
        dishRepository.deleteById(dishId);
    }
}
