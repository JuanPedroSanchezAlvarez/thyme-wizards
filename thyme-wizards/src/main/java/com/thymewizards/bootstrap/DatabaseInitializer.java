package com.thymewizards.bootstrap;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.thymewizards.domain.entity.Gender;
import com.thymewizards.dto.UserDTO;
import com.thymewizards.service.IUserService;

@Profile("init-db")
@Component
public class DatabaseInitializer implements CommandLineRunner {

	private final Faker faker = new Faker();
	private final IUserService userService;

	public DatabaseInitializer(IUserService userService) {
		this.userService = userService;
	}

    /**
     * Run this code at startup of the application
     */
	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 20; i++) {
			UserDTO fakeUser = createFakeUser();
			userService.save(fakeUser);
		}
	}

	private UserDTO createFakeUser() {
		UserDTO fakeUser = new UserDTO();
		fakeUser.setUserName(faker.name().username());
		fakeUser.setFirstName(faker.name().firstName());
		fakeUser.setLastName(faker.name().lastName());
		fakeUser.setGender(faker.bool().bool() ? Gender.MALE : Gender.FEMALE);
		fakeUser.setBirthday(LocalDate.ofInstant(faker.date().birthday(18, 65).toInstant(), ZoneId.systemDefault()));
		fakeUser.setEmail(faker.internet().emailAddress());
		fakeUser.setPhoneNumber(faker.phoneNumber().cellPhone());
		return fakeUser;
	}

}
