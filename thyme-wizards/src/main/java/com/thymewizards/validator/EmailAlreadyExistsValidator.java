package com.thymewizards.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import com.thymewizards.dto.UserDTO;
import com.thymewizards.service.IUserService;

public class EmailAlreadyExistsValidator implements ConstraintValidator<EmailAlreadyExists, UserDTO> {

	private final IUserService userService;

	@Autowired
	public EmailAlreadyExistsValidator(IUserService userService) {
		this.userService = userService;
	}

	public void initialize(EmailAlreadyExists constraint) {
		// This method is not needed in this example. This is useful when your custom annotation has extra parameters you want to read out.
	}

	public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {
		if (userService.emailAlreadyExists(dto.getEmail())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{EmailAlreadyExists}").addPropertyNode("email").addConstraintViolation();
			return false;
		}
		return true;
	}

}
