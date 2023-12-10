package com.example.delivery_control.dto;

import com.example.delivery_control.models.CartItem;
import com.example.delivery_control.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private int id;
    private UserEntity owner;
    private CartItem cartItem;
}
