package com.example.delivery_control.Repository;

import com.example.delivery_control.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndAndDishesList(Cart cart, Dish dish);

}
