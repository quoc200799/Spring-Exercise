package com.example.jwt;

import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.*;
import com.example.jwt.repository.*;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class JwtApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

//    @Test
//    void hihi(){
//        System.out.println(usernamePasswordAuthenticationToken.getName());
//    }
    @Test
    void save_roles() {
        List<Role> roles = List.of(
                new Role(null, "ADMIN"),
                new Role(null, "USER"),
                new Role(null, "AUTHOR")
        );

        roleRepository.saveAll(roles);
    }

    @Test
    void save_users() {
        Role userRole = roleRepository.findByName("USER").orElse(null);
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        Role authorRole = roleRepository.findByName("AUTHOR").orElse(null);

        List<User> users = List.of(
                new User(null, "Luong Quoc", "quoc@gmail.com", passwordEncoder.encode("111"), null, List.of(adminRole, userRole)),
                new User(null, "Ngoc Mai", "tu@gmail.com", passwordEncoder.encode("111"), null, List.of(userRole)),
                new User(null, "Thu Hằng", "luong@gmail.com", passwordEncoder.encode("111"), null, List.of(authorRole, userRole))
        );

        userRepository.saveAll(users);
    }

    @Test
    void save_category() {
        List<Category> categories = List.of(
                new Category(null, "Back-end"),
                new Category(null, "Front-end"),
                new Category(null, "Devops"),
                new Category(null, "Mobile"),
                new Category(null, "Database"),
                new Category(null, "Java"),
                new Category(null, "Javascript")

        );

        categoryRepository.saveAll(categories);
    }

    @Test
    void save_blogs() {
        Slugify slugify = Slugify.builder().build();
        Faker faker = new Faker();
        Random rd = new Random();
        List<User> userList = userRepository.findByRoles_NameIn(List.of("ADMIN", "AUTHOR"));
        List<Category> categoryList = categoryRepository.findAll();
        for (int i = 0; i < 25; i++) {
            // Random 1 user
            User rdUsser = userList.get(rd.nextInt(userList.size()));
            UserDto userDto = UserDto.builder()
                    .id(rdUsser.getId())
                    .email(rdUsser.getEmail())
                    .roles(rdUsser.getRoles())
                    .name(rdUsser.getName())
                    .avatar(rdUsser.getAvatar())
                    .build();
            // Random 1 ds category tương ứng
            List<Category> rdList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Category rdCategory = categoryList.get(rd.nextInt(categoryList.size()));
                if (!rdList.contains(rdCategory)) {
                    rdList.add(rdCategory);
                }
            }
            // Tạo blog
            Blog blog = Blog.builder()
                    .title(faker.book().title())
                    .slug(slugify.slugify(faker.book().title()))
                    .description(faker.book().publisher())
                    .content(faker.country().name())
                    .status(rd.nextInt(2) == 0)
                    .user(rdUsser)
                    .categories(rdList)
                    .build();
            blogRepository.save(blog);

        }
    }

    @Test
    void save_comments() {
        Random rd = new Random();
        Faker faker = new Faker();
        List<User> userList = userRepository.findAll();
        List<Blog> blogList = blogRepository.findAll();
        for (int i = 0; i < 100; i++) {
            User rdUser = userList.get(rd.nextInt(userList.size()));
            UserDto userDto = UserDto.builder()
                    .id(rdUser.getId())
                    .email(rdUser.getEmail())
                    .roles(rdUser.getRoles())
                    .name(rdUser.getName())
                    .avatar(rdUser.getAvatar())
                    .build();
            Blog rdBlog = blogList.get(rd.nextInt(blogList.size()));

            Comment comment = Comment.builder()
                    .content(faker.gameOfThrones().dragon())
                    .user(rdUser)
                    .blog(rdBlog)
                    .build();

            commentRepository.save(comment);
        }
    }
}
