package com.example.delivery_control.service.impl;

import com.example.delivery_control.Mapper.DishMapper;
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
import java.util.stream.Collectors;

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
        CartItem existcartItem = existingCartItem(cart, dish);
        if (existcartItem == null) {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            List<Dish> dishList = new ArrayList<>();
            dishList.add(dish);
            cartItem.setQuantityOfDish(dishquantity);
            cartItem.setDishesList(dishList);
            cartItemRepository.save(cartItem);
        } else {
            int currentQuantity = existcartItem.getQuantityOfDish();
            existcartItem.setQuantityOfDish(currentQuantity+dishquantity);
            cartItemRepository.save(existcartItem);
        }

    }
    @Override
    public List<DishDto> findAllCartItemDishes(String username) {
        List<Dish> dishList = new ArrayList<>();
        UserEntity userCart = userRepository.findByUsername(username);
        Cart cart = cartRepository.findByOwner(userCart);
        List<CartItem> existcartItemList = cartItemRepository.findByCart(cart);
        for (CartItem cartItem : existcartItemList) {
            List<Dish> cartItemDishes = cartItem.getDishesList();
            dishList.addAll(cartItemDishes);
        }
        return dishList.stream().map(DishMapper::mapToDishDto).collect(Collectors.toList());
    }

    public CartItem existingCartItem(Cart cart, Dish dish) {
        return cartItemRepository.findByCartAndDishesList(cart, dish);
    }
}
