package com.santander.exceptions.demo.mapper;

import com.santander.exceptions.demo.dto.UserDto;
import com.santander.exceptions.demo.entity.User;

public class UsersMapper {
    private UsersMapper() {}
    public static User dtoToUser(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getDni());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public static UserDto UserToDto(User user){
        UserDto userDto= new UserDto();
        userDto.setDni(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
