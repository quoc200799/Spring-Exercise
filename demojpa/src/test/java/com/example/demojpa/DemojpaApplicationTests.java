package com.example.demojpa;

import com.example.demojpa.dto.UserDto;
import com.example.demojpa.entity.User;
import com.example.demojpa.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//@SpringBootTest

// @springboottest
// Chỉ làm việc với database in-memory: h2 database
// Tạo ra các bean cần thiết, không chạy ứng dụng như //@SpringBootTest
// @DataJpaTest tự động @Rollback
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DemojpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    void save_user() {
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password("123456")
                    .build();

            userRepository.save(user);
        }
    }

    @Test
    @Rollback(value = false)
    void save_todo() {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password("123456")
                    .build();

            userRepository.save(user);
        }
    }

    @Test
    void get_all_user() {
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }
    @Test
    void get_by_id() {
        Optional<User> user = userRepository.findById(31);
        if(user.isPresent()){
            System.out.println(user.get());
        }else {
            throw new RuntimeException("Not found id");
        }
    }

    @Test
    @Rollback(value = false)
    void update_user() {
        Optional<User> userOptional = userRepository.findById(31);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setName("Luong Quoc");
            user.setEmail("quoc99@gmail.com");

            userRepository.save(user);

        }else {
            throw new RuntimeException("Not found id");
        }

    }
    @Test
    @Rollback(value = false)
    void delete_user() {
        Optional<User> userOptional = userRepository.findById(32);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            userRepository.delete(user);

        }else {
            throw new RuntimeException("Not found id");
        }


    }
    @Test
    void findUserDtoBy(){
        List<UserDto> userDtos = userRepository.findUserDto("Mose Leffler");
        userDtos.forEach(System.out::println);
    }

}
