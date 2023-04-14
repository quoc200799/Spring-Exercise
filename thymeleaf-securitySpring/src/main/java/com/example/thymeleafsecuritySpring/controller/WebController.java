package com.example.thymeleafsecuritySpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("name","Luong Quoc");
        List<String> users = List.of("Nguyễn thị", "Ngats","requider");
        model.addAttribute("users",users);
        return "index";
    }
    @GetMapping("/admin")
    public String getAdmin(){

        return "admin";
    }
    @GetMapping("/user")
    public String getUser(){
        return "user";
    }
    @GetMapping("/author")
    public String getAuthor(){
        return "author";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        return "login";
    }
}
