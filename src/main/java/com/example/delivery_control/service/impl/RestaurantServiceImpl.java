package com.example.delivery_control.service.impl;

import com.example.delivery_control.Mapper.RestaurantMapper;
import com.example.delivery_control.Repository.RestaurantRepository;
import com.example.delivery_control.Repository.UserRepository;
import com.example.delivery_control.dto.RestaurantDto;
import com.example.delivery_control.dto.ReviewDto;
import com.example.delivery_control.models.Restaurant;
import com.example.delivery_control.models.UserEntity;
import com.example.delivery_control.security.SecurityUtill;
import com.example.delivery_control.service.RestaurantService;
import com.example.delivery_control.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.delivery_control.Mapper.RestaurantMapper.mapToRestaurant;
import static com.example.delivery_control.Mapper.RestaurantMapper.mapToRestaurantDto;
@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ReviewService reviewService;

    @Override
    public List<RestaurantDto> findAllRestaurant() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        var restaurantDtoList = restaurants.stream().map(RestaurantMapper::mapToRestaurantDto).toList();
        for( var restaurantDto : restaurantDtoList)
        {
            countAvgRatingsAndAmount(restaurantDto);
        }
        return  restaurantDtoList;
    }

    @Override
    public Restaurant saveRestaurant(RestaurantDto restaurantDto) {
        String username = SecurityUtill.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Restaurant restaurant = mapToRestaurant(restaurantDto);
        restaurant.setCreated_by(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public RestaurantDto findRestaurantById(long restaurant_id) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).get();
        return mapToRestaurantDto(restaurant);
    }

    @Override
    public void updateRestaurant(RestaurantDto restaurantDto) {
        String username = SecurityUtill.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Restaurant restaurant = mapToRestaurant(restaurantDto);
        restaurant.setCreated_by(user);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public List<RestaurantDto> searchRestaurant(String query) {
        List<Restaurant> restaurants = restaurantRepository.searchRestaurant(query);
        return restaurants.stream().map(restaurant -> mapToRestaurantDto(restaurant)).collect(Collectors.toList());
    }

    @Override
    public void countAvgRatingsAndAmount(RestaurantDto restaurantDto) {
        List<ReviewDto> reviewDtoList = reviewService.findReviewByRestaurantId(restaurantDto.getId());
        if(reviewDtoList.isEmpty()){
            restaurantDto.setRestaurant_rating("Без отзывов");
            restaurantDto.setReviewCount(0);
            return;
        }
        double avgTemp=0;
        for ( var reviewDto : reviewDtoList ){
            avgTemp+=reviewDto.getRating();
        }
        if(avgTemp == 0){
            restaurantDto.setRestaurant_rating("Без отзывов");
            return;
        }
       avgTemp=avgTemp/reviewDtoList.size();
        int starRating = (int) avgTemp;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<starRating; i++){
            stringBuilder.append('☆');
        }
        String str = stringBuilder.toString();
        restaurantDto.setRestaurant_rating(String.format("%s %.2f", str, avgTemp));
        restaurantDto.setReviewCount(reviewDtoList.size());
    }

    @Override
    public double countAvgForChart(RestaurantDto restaurantDto) {
        List<ReviewDto> reviewDtoList = reviewService.findReviewByRestaurantId(restaurantDto.getId());
        if(reviewDtoList.isEmpty()){
            return 0;
        }
        double avgTemp=0;
        for ( var reviewDto : reviewDtoList ){
            avgTemp+=reviewDto.getRating();
        }
        if(avgTemp == 0){
            return 0;
        }
        avgTemp=avgTemp/reviewDtoList.size();
        int starRating = (int) avgTemp;
        return avgTemp;
    }

    @Override
    public List<List<Object>> restaurantChartData() {
        List<List<Object>> objectList= new ArrayList<>();
        List<Restaurant> restaurants = restaurantRepository.findAll();
        var restaurantDtoList = restaurants.stream().map(RestaurantMapper::mapToRestaurantDto).toList();
        for( var restaurantDto : restaurantDtoList)
        {
            objectList.add(List.of(restaurantDto.getRestaurant_name(), countAvgForChart(restaurantDto)));
        }
        return objectList;
    }

}
