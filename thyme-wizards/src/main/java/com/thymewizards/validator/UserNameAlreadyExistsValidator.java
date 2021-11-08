package com.thymewizards.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import com.thymewizards.dto.UserDTO;
import com.thymewizards.service.IUserService;

public class UserNameAlreadyExistsValidator implements ConstraintValidator<UserNameAlreadyExists, UserDTO> {

	private final IUserService userService;

	@Autowired
	public UserNameAlreadyExistsValidator(IUserService userService) {
		this.userService = userService;
	}

	public void initialize(UserNameAlreadyExists constraint) {
		// This method is not needed in this example. This is useful when your custom annotation has extra parameters you want to read out.
	}

	public boolean isValid(UserDTO formData, ConstraintValidatorContext context) {
		if (userService.userNameAlreadyExists(formData.getUserName())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{UserNameAlreadyExists}").addPropertyNode("userName").addConstraintViolation();
			return false;
		}
		return true;
	}

}
