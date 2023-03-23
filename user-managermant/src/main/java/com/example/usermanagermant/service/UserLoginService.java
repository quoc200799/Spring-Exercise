package com.example.usermanagermant.service;

import com.example.usermanagermant.exception.NotFoundException;
import com.example.usermanagermant.model.User;
import com.example.usermanagermant.model.UserLogin;
import com.example.usermanagermant.model.dto.UserDto;
import com.example.usermanagermant.model.dto.UserLoginDto;
import com.example.usermanagermant.model.mapper.UserLoginMapper;
import com.example.usermanagermant.model.mapper.UserMapper;
import com.example.usermanagermant.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserLoginService {
    @Autowired
    private UserLoginRepository userLoginRepository;

    public UserLoginDto getUsersById(String username,String pass) {
        for (UserLogin user : userLoginRepository.findAllUsers()) {
            if (Objects.equals(user.getUsername(), username) &&Objects.equals(user.getPassword(), pass)) {
                return UserLoginMapper.toUserDto(user);
            }
        }
        throw new NotFoundException("Sai tên đăng nhập hoặc mật khẩu");
    }
}
