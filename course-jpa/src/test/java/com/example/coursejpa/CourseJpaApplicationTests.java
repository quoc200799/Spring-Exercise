package com.example.coursejpa;

import com.example.coursejpa.entity.Category;
import com.example.coursejpa.entity.Course;
import com.example.coursejpa.entity.User;
import com.example.coursejpa.repository.CategoryRepository;
import com.example.coursejpa.repository.CourseRepository;
import com.example.coursejpa.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.jsf.FacesContextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CourseJpaApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void save_category() {
        Faker faker = new Faker();
        for (int i = 0; i < 6; i++) {
            Category category = Category.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();
            categoryRepository.save(category);
        }
    }

    @Test
    void save_user() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .name(faker.name().firstName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().cellPhone())
                    .avatar(faker.company().logo())
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void save_course() {
        Faker faker = new Faker();
        Random rd = new Random();

        List<User> users = userRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        for (int i = 0; i < 20; i++) {
            //Random 1 user
            User rduser = users.get(rd.nextInt(users.size()));
            // Random 1 ds category
            List<Category> categoryList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Category rdCategory = categories.get(rd.nextInt(categories.size()));
                if (!categoryList.contains(rdCategory)) {
                    categoryList.add((rdCategory));
                }

            }
            Course course = Course.builder()
                    .name(faker.book().title())
                    .description(faker.lorem().sentence(15))
                    .type(rd.nextInt(2) == 0 ? "online" : "onlab")
                    .thumbnail(faker.company().logo())
                    .price(faker.number().numberBetween(500_000,3_000_000))
                    .rating(faker.number().randomDouble(1,3,5))
                    .user(rduser)
                    .categories(categoryList)
                    .build();
            courseRepository.save(course);
        }
    }
    @Test
    void findCourse(){
        List<Course> courses = categoryRepository.findCourse(null,"online", null);
        System.out.println(courses.size());
    }
}
