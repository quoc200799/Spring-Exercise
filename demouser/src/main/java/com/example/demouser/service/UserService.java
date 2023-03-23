package com.example.demouser.service;

import com.example.demouser.model.User;
import com.example.demouser.model.dto.UserDto;
import com.example.demouser.model.response.FileResponse;
import com.example.demouser.request.CreateUserRequest;
import com.example.demouser.request.UpdateUserPassword;
import com.example.demouser.request.UpdateUserRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service

public interface UserService {
    public ArrayList<UserDto> getUsers();
    public User deleteUser(String id);
    public UserDto getUsersById(String id);
    public User createUser(CreateUserRequest createUserRequest);
    void validate(CreateUserRequest request);
    public UserDto updateUser(String id, UpdateUserRequest request);
    public void updatePassword(String id, UpdateUserPassword request);
    public String forgotPassword(String id);
    public FileResponse updateAvatar(String id, MultipartFile file);
}
