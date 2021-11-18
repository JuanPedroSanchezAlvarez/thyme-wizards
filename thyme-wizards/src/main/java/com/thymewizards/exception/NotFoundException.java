package com.thymewizards.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 301706336447442306L;
	public NotFoundException(UUID id) {
        super(String.format("Element with id %s not found.", id.toString()));
    }
}
