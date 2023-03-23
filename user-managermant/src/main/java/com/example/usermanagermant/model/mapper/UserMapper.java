package com.example.usermanagermant.model.mapper;

import com.example.usermanagermant.model.User;
import com.example.usermanagermant.model.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user){
        UserDto tmp = new UserDto();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setAvatar(user.getAvatar());
        tmp.setAddress(user.getAddress());
        return tmp;
    }
}
