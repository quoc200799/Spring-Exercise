package com.example.demouser.model.mapper;

import com.example.demouser.model.User;
import com.example.demouser.model.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user){
        UserDto tmp = new UserDto();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setAvatar(user.getAvatar());
        tmp.setAndress(user.getAddress());
        return tmp;
    }
}
