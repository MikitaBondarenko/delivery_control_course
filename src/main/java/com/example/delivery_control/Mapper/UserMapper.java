package com.example.delivery_control.Mapper;

import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.models.UserEntity;

public class UserMapper {
    public static UserDto mapToUserDro(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword()).build();
    }
}
