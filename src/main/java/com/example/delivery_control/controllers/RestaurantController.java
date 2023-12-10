package com.example.delivery_control.controllers;

import com.example.delivery_control.dto.RestaurantDto;
import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.security.SecurityUtill;
import com.example.delivery_control.service.RestaurantService;
import com.example.delivery_control.service.ReviewService;
import com.example.delivery_control.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@Data
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final ReviewService reviewService;


    @GetMapping("/restaurants")
    public String listRestaurant(Model model) {
        UserDto user = new UserDto();
        String username = SecurityUtill.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        List<RestaurantDto> restaurantDtoList = restaurantService.findAllRestaurant();
        model.addAttribute("restaurant", restaurantDtoList);
        return "restaurant-list";
    }

    @GetMapping("/restaurants/addNew")
    public String createRestaurantForm(Model model) {
        UserDto user = new UserDto();
        String username = SecurityUtill.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        RestaurantDto restaurant = new RestaurantDto();
        model.addAttribute("restaurant", restaurant);
        return "restaurant-create";
    }

    @PostMapping("/restaurants/addNew")
    public String saveRestaurant(@Valid @ModelAttribute("restaurant") RestaurantDto restaurantDto,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("restaurant", restaurantDto);
            return "restaurant-create";
        }
        restaurantService.saveRestaurant(restaurantDto);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants/{restaurant_id}/edit")
    public String editRestaurant(@PathVariable("restaurant_id") long restaurant_id, Model model) {
        RestaurantDto restaurant = restaurantService.findRestaurantById(restaurant_id);
        UserDto user = new UserDto();
        String username = SecurityUtill.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("restaurant", restaurant);
        return "restaurant-edit";
    }

    @PostMapping("/restaurants/{restaurant_id}/edit")
    public String updateRestaurant(@PathVariable("restaurant_id") long restaurant_id,
                                   @Valid
                                   @ModelAttribute("restaurant") RestaurantDto restaurantDto,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("restaurant", restaurantDto);
            return "restaurant-edit";
        }
        restaurantDto.setId((int) restaurant_id);
        restaurantService.updateRestaurant(restaurantDto);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants/{restaurant_id}")
    public String restaurantDetail(@PathVariable("restaurant_id") long restaurant_id, Model model) {
        UserDto user = new UserDto();
        RestaurantDto restaurantDto = restaurantService.findRestaurantById(restaurant_id);
        String username = SecurityUtill.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("restaurant", restaurantDto);
        return "restaurant-detail";
    }

    @GetMapping("/restaurantsDish/{restaurant_id}")
    public String restaurantDetailDish(@PathVariable("restaurant_id") long restaurant_id, Model model) {
        UserDto user = new UserDto();
        RestaurantDto restaurantDto = restaurantService.findRestaurantById(restaurant_id);
        String username = SecurityUtill.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("restaurant", restaurantDto);
        return "restaurant-detailDish";
    }

    @GetMapping("/restaurants/{restaurant_id}/delete")
    public String restaurantDelete(@PathVariable("restaurant_id") Long restaurant_id) {
        restaurantService.delete(restaurant_id);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants-search")
    public String searchRestaurant(@RequestParam(value = "query") String query, Model model) {
        UserDto user = new UserDto();
        String username = SecurityUtill.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        List<RestaurantDto> restaurantDtoList = restaurantService.searchRestaurant(query);
        model.addAttribute("restaurant", restaurantDtoList);
        return "restaurant-list";
    }

    @GetMapping("/chart")
    public String chartOfRestaurants(Model model) {
        UserDto user = new UserDto();
        String username = SecurityUtill.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("chartData", restaurantService.restaurantChartData());
        return "chart";
    }
}
