package com.example.delivery_control.service.impl;

import com.example.delivery_control.Mapper.UserMapper;
import com.example.delivery_control.Repository.RoleRepository;
import com.example.delivery_control.Repository.UserRepository;
import com.example.delivery_control.dto.RegistrationDto;
import com.example.delivery_control.dto.RestaurantDto;
import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.models.Restaurant;
import com.example.delivery_control.models.Role;
import com.example.delivery_control.models.UserEntity;
import com.example.delivery_control.security.SecurityUtill;
import com.example.delivery_control.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.example.delivery_control.Mapper.RestaurantMapper.mapToRestaurant;
import static com.example.delivery_control.Mapper.UserMapper.mapToUser;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        UserEntity user = mapToUser(userDto);
        userRepository.save(user);
    }


    @Override
    public UserDto findByEmail(String email) {
        var user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        UserEntity user =userRepository.findByUsername(username);
        if(user == null){
            return null;
        }
        UserDto userDto = UserMapper.mapToUserDto(user);
        userDto.setUserRole(user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN")));
        return userDto;
    }
}
