package com.example.delivery_control.controllers;

import com.example.delivery_control.dto.DishDto;
import com.example.delivery_control.dto.ReviewDto;
import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.models.Dish;
import com.example.delivery_control.models.Review;
import com.example.delivery_control.security.SecurityUtill;
import com.example.delivery_control.service.DishService;
import com.example.delivery_control.service.ReviewService;
import com.example.delivery_control.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@AllArgsConstructor
@Controller
public class DishController {
    private final DishService dishService;
    private final UserService userService;
    @GetMapping("/dishes/{restaurant_id}/new")
    public String createDishForm(@PathVariable("restaurant_id") Long restaurant_id, Model model)
    {
        Dish dish = new Dish();
        model.addAttribute("restaurant_id", restaurant_id);
        model.addAttribute("dish", dish);
        return "dish-create";
    }

    @PostMapping("/dishes/{restaurant_id}")
    public String createDish(@PathVariable("restaurant_id") Long restaurant_id,
                               @Valid
                               @ModelAttribute("dish") DishDto dishDto,
                               BindingResult result, Model model)
    {
        if(result.hasErrors()){
            model.addAttribute("dish", dishDto);
            return "dish-create";
        }
        dishService.createDish(restaurant_id, dishDto);
        return "redirect:/restaurantsDish/" + restaurant_id;
    }

    @GetMapping("/dishes/{dish_id}")
    public String viewReview(@PathVariable("dish_id") Long dish_id, Model model){
        UserDto user = new UserDto();
        DishDto dishDto =  dishService.findByDishId(dish_id);
        String username = SecurityUtill.getSessionUser();
        if(username != null){
            user=userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("dish", dishDto);
        return "dish-detail";
    }

    @GetMapping("/dishes/{dish_id}/edit")
    public String editDish(@PathVariable("dish_id") long dish_id, Model  model){
        DishDto dishDto = dishService.findByDishId(dish_id);
        model.addAttribute("dish" , dishDto);
        return "dish-edit";
    }

    @PostMapping("/dishes/{dish_id}/edit")
    public String updateDish(@PathVariable("dish_id") long dish_id,
                               @Valid
                               @ModelAttribute("dish") DishDto dishDto,
                               BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("dish", dishDto);
            return "dish-edit";
        }
        DishDto dishDto1 = dishService.findByDishId(dish_id);
        dishDto.setId(dish_id);
        dishDto.setRestaurant(dishDto1.getRestaurant());
        dishService.updateDish(dishDto);
        return "redirect:/restaurants";
    }

    @GetMapping("/dishes/{dish_id}/delete")
    public  String deleteDish(@PathVariable("dish_id") long dish_id,
                                @ModelAttribute("dish") DishDto dishDto){
        dishDto = dishService.findByDishId(dish_id);
        dishService.deleteDish(dish_id);
        return  "redirect:/restaurantsDish/" + dishDto.getRestaurant().getId();
    }
}
