package com.example.delivery_control.Repository;

import com.example.delivery_control.models.Cart;
import com.example.delivery_control.models.Role;
import com.example.delivery_control.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByOwner(UserEntity user);
}
