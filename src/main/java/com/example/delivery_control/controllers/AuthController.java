package com.example.delivery_control.controllers;

import com.example.delivery_control.dto.RegistrationDto;
import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.models.UserEntity;
import com.example.delivery_control.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@AllArgsConstructor
@Controller
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result, Model model) {
        UserDto existingUsersEmail = userService.findByEmail(user.getEmail());
        if(existingUsersEmail != null && existingUsersEmail.getEmail() != null
                                      && !existingUsersEmail.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        UserDto existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null
                && !existingUserUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/restaurants?success";
    }

}
