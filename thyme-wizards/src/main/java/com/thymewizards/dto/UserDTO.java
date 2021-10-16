package com.thymewizards.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.thymewizards.domain.entity.Gender;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {

	@NotBlank
	@Size(max = 25)
	private String userName;

	@NotBlank
	@Size(max = 25)
	private String firstName;

	@NotBlank
	@Size(max = 25)
	private String lastName;

	@NotNull
	private Gender gender;

	@NotNull
	@Past
	private LocalDate birthday;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Pattern(regexp="(^$|[0-9]{9})")
	private String phoneNumber;

}
