package com.thymewizards.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class BaseDTO {

	private UUID id;
	private Long version;

}
