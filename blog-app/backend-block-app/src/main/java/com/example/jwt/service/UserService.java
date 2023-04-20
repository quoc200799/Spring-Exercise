package com.example.jwt.service;

import com.example.jwt.entity.Category;
import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import com.example.jwt.repository.RoleRepository;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.request.UpsertUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Page<User> getAllUser(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<User> pagedResult = userRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }

    public String updateUserById(Integer id, UpsertUserRequest request) {
        validation(request);
        User user = userRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("not found id");
        });
        List<Role> roleList =  findRolesById(request.getRoleIds());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setRoles(roleList);
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return "Cập nhật thành công!";
    }


    public String createNewUser(UpsertUserRequest request) {
        validation(request);
        List<Role> roleList =  findRolesById(request.getRoleIds());
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .roles(roleList)
                .password(request.getPassword())
                .build();
        userRepository.save(user);
        return "Thêm mới thành công";

    }

    private void validation(UpsertUserRequest request) {
        if (!request.getEmail().trim().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")) {
            throw new RuntimeException("Email khoong dung dinh dang!");
        }
        if (request.getName().trim().equals("")) {
            throw new RuntimeException("Ten khong duoc de trong");
        }
        if (request.getPassword().trim().length() >= 3) {
            throw new RuntimeException("Mat khau phai nhieu hon 3 ki tu");

        }
        if (request.getRoleIds().size() ==0) {
            throw new RuntimeException("Phan quyen cho nguoi dung");

        }
    }
    public List<Role> findRolesById(List<Integer> roleId) {
        List<Role> roleList = new ArrayList<>();
        for (Integer integer : roleId) {
            Optional<Role> roleOptional = roleRepository.findById(integer);
            if (roleOptional.isPresent()) {
                roleList.add(roleOptional.get());
            } else {
                throw new RuntimeException("Not found role id");
            }
        }
        return roleList;
    }

    public String deleteUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("not found id");
        });
        userRepository.delete(user);
        return "Xóa thành công!";
    }
}
