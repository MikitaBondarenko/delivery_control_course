package com.example.delivery_control.dto;

import com.example.delivery_control.models.Cart;
import com.example.delivery_control.models.Dish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private int id;
    private Cart cart;
    private List<DishDto> dishesDtoList;
}
