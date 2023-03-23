package com.example.usermanagermant.repository;

import com.example.usermanagermant.db.UserDb;
import com.example.usermanagermant.db.UserLoginDb;
import com.example.usermanagermant.model.User;
import com.example.usermanagermant.model.UserLogin;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class UserLoginRepository {
    public ArrayList<UserLogin> findAllUsers(){
        return UserLoginDb.users;
    }
    public Optional<UserLogin> findByLogin(String username, String pass) {
        return UserLoginDb.users
                .stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(pass))
                .findFirst();
    }

}
