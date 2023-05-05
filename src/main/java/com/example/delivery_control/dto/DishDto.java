package com.example.delivery_control.dto;

import com.example.delivery_control.models.Restaurant;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {
    private Long id;
    @NotEmpty(message = "Введите имя блюда!")
    private String name;
    private double price;
    private String dish_imgurl;
    @NotEmpty(message = "Введите описание блюда!")
    private String description;
    private Restaurant restaurant;
}
