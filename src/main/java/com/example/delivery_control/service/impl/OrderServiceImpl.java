package com.example.delivery_control.service.impl;

import com.example.delivery_control.Repository.CartItemRepository;
import com.example.delivery_control.Repository.CartRepository;
import com.example.delivery_control.Repository.DishRepository;
import com.example.delivery_control.Repository.UserRepository;
import com.example.delivery_control.dto.DishDto;
import com.example.delivery_control.models.Cart;
import com.example.delivery_control.models.CartItem;
import com.example.delivery_control.models.Dish;
import com.example.delivery_control.models.UserEntity;
import com.example.delivery_control.service.DishService;
import com.example.delivery_control.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.delivery_control.Mapper.DishMapper.mapToDishDto;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void addDishToCart(String username, Long dish_id, int dishquantity) {
        Dish dish = dishRepository.findById(dish_id).get();
        UserEntity userCart = userRepository.findByUsername(username);
        Cart cart = cartRepository.findByOwner(userCart);
        CartItem existcartItem = existingDish(cart, dish);
        if (existcartItem == null) {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            List<Dish> dishList = new ArrayList<>();
            for (int i = 0; i < dishquantity; i++) {
                dishList.add(dish);
            }
            cartItem.setDishesList(dishList);
            cartItemRepository.save(cartItem);
        } else {
            List<Dish> dishList = existcartItem.getDishesList();
            for (int i = 0; i < dishquantity; i++) {
                dishList.add(dish);
            }
            existcartItem.setDishesList(dishList);
            cartItemRepository.save(existcartItem);
        }

    }

    public CartItem existingDish(Cart cart, Dish dish) {
        return cartItemRepository.findByCartAndAndDishesList(cart, dish);
    }
}
