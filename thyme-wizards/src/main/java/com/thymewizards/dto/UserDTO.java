package com.thymewizards.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.thymewizards.domain.entity.Gender;
import com.thymewizards.validator.EmailAlreadyExists;
import com.thymewizards.validator.UserNameAlreadyExists;
import com.thymewizards.validator.ValidationGroupOne;
import com.thymewizards.validator.ValidationGroupTwo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@UserNameAlreadyExists(groups = ValidationGroupTwo.class)
@EmailAlreadyExists(groups = ValidationGroupTwo.class)
public class UserDTO extends BaseDTO {

	@NotBlank
	@Size(min = 1, max = 25, groups = ValidationGroupOne.class)
	private String userName;

	@NotBlank
	@Size(min = 1, max = 25, groups = ValidationGroupOne.class)
	private String firstName;

	@NotBlank
	@Size(min = 1, max = 25, groups = ValidationGroupOne.class)
	private String lastName;

	@NotNull
	private Gender gender;

	@NotNull
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	@NotBlank
	@Size(min = 15, max = 50, groups = ValidationGroupOne.class)
	@Email(groups = ValidationGroupOne.class)
	private String email;

	@NotBlank
	@Pattern(regexp = "(^$|[0-9]{9})", groups = ValidationGroupOne.class)
	private String phoneNumber;


	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}

}
