package com.example.delivery_control.dto;

import com.example.delivery_control.models.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private int id;
    @NotEmpty(message = "Должно быть указано имя")
    private String restaurant_name;
    @NotEmpty(message = "Должен быть указан адрес")
    private String restaurant_address;
    @NotEmpty(message = "Должен быть указан телефон")
    private String restaurant_phone;
    @NotEmpty(message = "Должна быть фотография")
    private String restaurant_imgurl;
    @NotEmpty(message = "Должен быть указан сайт")
    private String restaurant_site;
    private UserEntity created_by;
    private List<ReviewDto> reviewDtoList;
    private List<DishDto> dishDtoList;
    private int restaurant_rating;
    private int reviewCount;
}


