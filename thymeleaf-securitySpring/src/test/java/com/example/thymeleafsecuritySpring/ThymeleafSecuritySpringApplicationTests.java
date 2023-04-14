package com.example.thymeleafsecuritySpring;

import com.example.thymeleafsecuritySpring.entity.Role;
import com.example.thymeleafsecuritySpring.repository.RoleRepository;
import com.example.thymeleafsecuritySpring.repository.UserRepository;
import com.example.thymeleafsecuritySpring.entity.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ThymeleafSecuritySpringApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Test
    void save_role() {
        List<Role> roles = new ArrayList<>(List.of(
                new Role(null, "USER"),
                new Role(null, "ADMIN"),
                new Role(null, "AUTHOR")
        ));
        roleRepository.saveAll(roles);
    }
    @Test
    void save_user() {
        Role roleUser = roleRepository.findByName("USER").orElse(null);
        Role roleAdmin = roleRepository.findByName("ADMIN").orElse(null);
        Role roleAuthor = roleRepository.findByName("AUTHOR").orElse(null);

        List<User> users = List.of(
                new User(null, "Luong Quoc", "quoc@gmail.com", encoder.encode("111"),false,null, List.of(roleAdmin, roleUser)),
                new User(null, "Ngoc Mai", "tu@gmail.com", encoder.encode("111"),false,null, List.of(roleUser)),
                new User(null, "Thu Hằng", "luong@gmail.com", encoder.encode("111"), false,null,List.of(roleAuthor))
        );

        userRepository.saveAll(users);

        }



}
