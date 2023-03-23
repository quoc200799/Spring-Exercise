package com.example.demouser.repository;

import com.example.demouser.db.UserDb;
import com.example.demouser.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class UserRepository {
    public ArrayList<User> findAllUsers(){
        return UserDb.users;
    }

    public Optional<User> findById(String id) {
        return UserDb.users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    public Optional<User> findByEmail(String email) {
        return UserDb.users
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public void deleteById(String id) {
        UserDb.users.removeIf(user -> user.getId().equals(id) );
    }

    public void save(User user) {
        UserDb.users.add(user);
    }

}
