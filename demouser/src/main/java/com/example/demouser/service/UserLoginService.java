package com.example.demouser.service;

import com.example.demouser.exception.NotFoundException;
import com.example.demouser.model.User;
import com.example.demouser.model.UserLogin;
import com.example.demouser.model.dto.UserDto;
import com.example.demouser.model.dto.UserLoginDto;
import com.example.demouser.model.mapper.UserLoginMapper;
import com.example.demouser.model.mapper.UserMapper;
import com.example.demouser.repository.UserLoginRepository;
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
