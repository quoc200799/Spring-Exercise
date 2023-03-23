package com.example.usermanagermant.model.mapper;

import com.example.usermanagermant.model.User;
import com.example.usermanagermant.model.UserLogin;
import com.example.usermanagermant.model.dto.UserDto;
import com.example.usermanagermant.model.dto.UserLoginDto;

public class UserLoginMapper {
    public static UserLoginDto toUserDto(UserLogin user){
        UserLoginDto tmp = new UserLoginDto();
        tmp.setId(user.getId());
        tmp.setUsername(user.getUsername());
        tmp.setEmail(user.getEmail());
        tmp.setAvatar(user.getAvatar());
        return tmp;
    }
}
