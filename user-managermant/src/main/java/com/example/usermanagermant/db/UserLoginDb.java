package com.example.usermanagermant.db;

import com.example.usermanagermant.model.User;
import com.example.usermanagermant.model.UserLogin;

import java.util.ArrayList;
import java.util.List;

public class UserLoginDb {
    public static ArrayList<UserLogin> users = new ArrayList<>(List.of(
            new UserLogin(1, "luongquoc", "quoc200799@gmail.com", "213","avt1"),
            new UserLogin(2, "anh299", "luong23@gmail.com",  "322","avt2"),
            new UserLogin(3, "pro99", "chuc9x@gmail.com", "890","avt3")
    ));
}
