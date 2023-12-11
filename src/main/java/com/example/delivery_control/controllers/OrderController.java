package com.example.delivery_control.controllers;

import com.example.delivery_control.dto.RestaurantDto;
import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.security.SecurityUtill;
import com.example.delivery_control.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@Data
public class OrderController {
    private final UserService userService;

    @GetMapping("/cart")
    public String listRestaurant(Model model) {
        UserDto user = new UserDto();
        String username = SecurityUtill.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        return "cart";
    }
}
