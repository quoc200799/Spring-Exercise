package com.example.usermanagermant.service;

import com.example.usermanagermant.model.User;
import com.example.usermanagermant.model.dto.UserDto;
import com.example.usermanagermant.model.response.FileResponse;
import com.example.usermanagermant.request.CreateUserRequest;
import com.example.usermanagermant.request.UpdateUserPassword;
import com.example.usermanagermant.request.UpdateUserRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service

public interface UserService {
    public List<UserDto> getUsers();
    public User deleteUser(Integer id);
    public UserDto getUsersById(Integer id);
    public User createUser(CreateUserRequest createUserRequest);
    void validate(CreateUserRequest request);
    public User updateUser(Integer id, UpdateUserRequest request);
    public void updatePassword(Integer id, UpdateUserPassword request);
    public String forgotPassword(Integer id);
    public FileResponse updateAvatar(Integer id, MultipartFile file);
}
