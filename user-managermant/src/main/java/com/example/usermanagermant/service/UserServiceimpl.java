package com.example.usermanagermant.service;

import com.example.usermanagermant.exception.NotFoundException;
import com.example.usermanagermant.model.User;
import com.example.usermanagermant.model.dto.UserDto;
import com.example.usermanagermant.model.mapper.UserMapper;
import com.example.usermanagermant.model.response.FileResponse;
import com.example.usermanagermant.repository.UserRepository;
import com.example.usermanagermant.request.CreateUserRequest;
import com.example.usermanagermant.request.UpdateUserPassword;
import com.example.usermanagermant.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private MailService mailService;

//    private String generateId() {
//        String uuid = UUID.randomUUID().toString();
//        return uuid;
//
//    }

    public List<UserDto> getUsers() {
        List<UserDto> userDtos = userRepository.getUserDtoUsingNativeQuery();
        return userDtos;
    }

    public User deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("Not found id");
        }
    }

    @Override
    public UserDto getUsersById(Integer id) {
        Optional<UserDto> user = userRepository.findUserDtoById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("Not found id");
        }
    }

    @Override
    public User createUser(CreateUserRequest request) {
        validate(request);
        //builder thay cho contructor.
        User user = User.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
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
    public User updateUser(Integer id, UpdateUserRequest request) {
        for (User t : userRepository.findAll()) {
            if (Objects.equals(t.getId(), id)) {
                t.setName(request.getName());
                t.setAddress(request.getAddress());
                t.setEmail(request.getEmail());
                t.setPhone(request.getPhone());
                userRepository.save(t);
                return t;
            }
        }
        throw new RuntimeException("Not found id");
    }

    // Cập nhật password mới
    public void updatePassword(Integer id, UpdateUserPassword request) {
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
        userRepository.save(user);
    }

    // Quên mật khẩu
    public String forgotPassword(Integer id) {
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
        userRepository.save(user);
        return newPassword;
    }

    public FileResponse updateAvatar(Integer id, MultipartFile file) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });

        // Upload file
        FileResponse fileResponse = fileService.uploadFile(file);

        // Set lại avatar của user
        user.setAvatar(fileResponse.getUrl());
        userRepository.save(user);
        return fileResponse;
    }
}
