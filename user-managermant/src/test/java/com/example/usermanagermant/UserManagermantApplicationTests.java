package com.example.usermanagermant;

import com.example.usermanagermant.model.User;
import com.example.usermanagermant.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserManagermantApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Rollback(value = false)
	void save_user() {
		Faker faker = new Faker();
		for (int i = 0; i < 20; i++) {
			User user = User.builder()
					.name(faker.name().fullName())
					.email(faker.internet().emailAddress())
					.phone(faker.phoneNumber().phoneNumber())
					.address(faker.address().fullAddress())
					.address(faker.address().fullAddress())
					.password("123456")
					.build();
			userRepository.save(user);
		}
	}

}
