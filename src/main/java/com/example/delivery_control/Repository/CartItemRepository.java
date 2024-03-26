package com.example.delivery_control.Repository;

import com.example.delivery_control.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndDishesList(Cart cart, Dish dish);
    List<CartItem> findByCart(Cart cart);
}
