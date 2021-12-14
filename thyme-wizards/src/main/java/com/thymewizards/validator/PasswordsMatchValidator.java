package com.thymewizards.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.thymewizards.dto.UserDTO;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UserDTO> {

	public void initialize(PasswordsMatch constraint) {
		// This method is not needed in this example. This is useful when your custom annotation has extra parameters you want to read out.
	}

	public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {
		if (!dto.getPassword().equals(dto.getPasswordRepeated())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{PasswordsNotMatching}").addPropertyNode("passwordRepeated").addConstraintViolation();
			return false;
		}
		return true;
	}

}
