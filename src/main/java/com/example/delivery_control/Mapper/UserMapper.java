package com.example.delivery_control.Mapper;

import com.example.delivery_control.dto.UserDto;
import com.example.delivery_control.models.UserEntity;

public class UserMapper {
    public static UserDto mapToUserDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .surname(user.getSurname())
                .firstname(user.getFirstname())
                .address(user.getAddress())
                .phone(user.getPhone())
                .avatarImg(user.getAvatarImg())
                .username(user.getUsername())
                .password(user.getPassword()).build();
    }
    public  static UserEntity mapToUser(UserDto user){
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .surname(user.getSurname())
                .firstname(user.getFirstname())
                .address(user.getAddress())
                .phone(user.getPhone())
                .avatarImg(user.getAvatarImg())
                .username(user.getUsername())
                .password(user.getPassword()).build();
    }
}
