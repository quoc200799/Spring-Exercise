package com.example.demouser.model.mapper;

import com.example.demouser.model.User;
import com.example.demouser.model.UserLogin;
import com.example.demouser.model.dto.UserDto;
import com.example.demouser.model.dto.UserLoginDto;

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
