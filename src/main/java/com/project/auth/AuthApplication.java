package com.project.auth;

import com.project.auth.entity.User;
import com.project.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialUserData(UserRepository repository){
		return (args) -> {
			User user = new User("test1@test.it", "Tom Hanks", "1234", "ADMIN");
			repository.save(user);

			user = new User("tom@gmail.com", "Tim Duncan", "1234", "USER");
			repository.save(user);

			user = new User("pec@yahoo.com", "Marc marquez", "1234", "RIDER");
			repository.save(user);

		};
	}

}
