package com.example.delivery_control.controllers;

import com.example.delivery_control.dto.RegistrationDto;
import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.models.UserEntity;
import com.example.delivery_control.security.SecurityUtill;
import com.example.delivery_control.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public String loginPage(Model model){
        UserDto user = new UserDto();
        String username = SecurityUtill.getSessionUser();
        if(username != null){
            user=userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
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

    @PostMapping("*/userInfoSave")
    public  String editUserInfo(@Valid @ModelAttribute("user") UserDto user,
                                BindingResult result, Model model, HttpServletRequest request){
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "restaurant-list";
        }
        UserDto userToUpdate = new UserDto();
        String username = SecurityUtill.getSessionUser();
        if(username != null){
            userToUpdate=userService.findByUsername(username);
        }
        else return "restaurant-list";
        user.setId(userToUpdate.getId());
        user.setEmail(userToUpdate.getEmail());
        user.setPassword(userToUpdate.getPassword());
        user.setUsername(userToUpdate.getUsername());
        userService.updateUser(user);

        return "redirect:/restaurants";
    }

}
