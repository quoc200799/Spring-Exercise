package com.example.demouser.service;

import com.example.demouser.exception.NotFoundException;
import com.example.demouser.model.User;
import com.example.demouser.model.dto.UserDto;
import com.example.demouser.model.mapper.UserMapper;
import com.example.demouser.model.response.FileResponse;
import com.example.demouser.repository.UserRepository;
import com.example.demouser.request.CreateUserRequest;
import com.example.demouser.request.UpdateUserPassword;
import com.example.demouser.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private MailService mailService;

    private String generateId() {
        String uuid = UUID.randomUUID().toString();
        return uuid;

    }

    public ArrayList<UserDto> getUsers() {
        ArrayList<UserDto> result = new ArrayList<>();
        for (User user : userRepository.findAllUsers()) {
            result.add(UserMapper.toUserDto(user));
        }
        return result;

    }

    public User deleteUser(String id) {
        userRepository.findAllUsers().removeIf(item -> Objects.equals(item.getId(), id));
        return null;
    }

    @Override
    public UserDto getUsersById(String id) {
        for (User user : userRepository.findAllUsers()) {
            if (Objects.equals(user.getId(), id)) {
                return UserMapper.toUserDto(user);
            }
        }
        throw new NotFoundException("User not found");
    }

    @Override
    public User createUser(CreateUserRequest request) {
        validate(request);
        //builder thay cho contructor.
        User user = User.builder()
                .id(generateId())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .build();
        userRepository.findAllUsers().add(user);
        return user;
    }

    public void validate(CreateUserRequest request) {
        if (request.getName().trim().equals("")) {
            throw new NotFoundException("Tên không được để trống");
        } else if (request.getAddress().trim().equals("")) {
            throw new NotFoundException("Địa chỉ không được để trống");
        } else if (request.getEmail().trim().equals("")) {
            throw new NotFoundException("Email không được để trống");
        } else if (request.getPhone().trim().equals("")) {
            throw new NotFoundException("Số điện thoại không được để trống");
        } else if (request.getPassword().trim().length() > 6) {
            throw new NotFoundException("Mật khẩu phải nhiều hơn 6 kí tự");
        }
    }

    // Cập nhật thông tin của user
    public UserDto updateUser(String id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });

        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAndress());

        return UserMapper.toUserDto(user);
    }

    // Cập nhật password mới
    public void updatePassword(String id, UpdateUserPassword request) {
        // Kiểm tra có tồn tại hay không
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });

        // Kiểm tra oldPassword có đúng không
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new NotFoundException("old password is incorrect!");
        }

        // Kiểm tra oldPassword có = newPassword không
        if (request.getNewPassword().equals(request.getOldPassword())) {
            throw new NotFoundException("old password and new password cannot be the same!");
        }

        // Cập nhật newPassword cho user tương ứng
        user.setPassword(request.getNewPassword());
    }

    // Quên mật khẩu
    public String forgotPassword(String id) {
        // Kiểm tra user có tồn tại hay không
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
        // Random chuỗi password mới cho user (100 -> 999)
        Random rd = new Random();
        String newPassword = String.valueOf(rd.nextInt(900) + 100);

        // Lấy thông tin của user và đặt lại password mới cho user
        user.setPassword(newPassword);
        // Gửi mail
//        mailService.sendMail(
//                user.getEmail(),
//                "Quên mật khẩu",
//                "Mật khẩu mới: " + newPassword);
        // Trả về thông tin password mới
        return newPassword;
    }

    public FileResponse updateAvatar(String id, MultipartFile file) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });

        // Upload file
        FileResponse fileResponse = fileService.uploadFile(file);

        // Set lại avatar của user
        user.setAvatar(fileResponse.getUrl());
        return fileResponse;
    }
}
