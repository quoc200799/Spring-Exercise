package com.example.demouser.repository;

import com.example.demouser.db.UserDb;
import com.example.demouser.db.UserLoginDb;
import com.example.demouser.model.User;
import com.example.demouser.model.UserLogin;
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
