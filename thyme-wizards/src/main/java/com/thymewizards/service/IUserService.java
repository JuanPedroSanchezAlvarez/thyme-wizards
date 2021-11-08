package com.thymewizards.service;

import java.util.UUID;

import com.thymewizards.dto.UserDTO;

public interface IUserService extends ICrudService<UserDTO, UUID> {

	Boolean userNameAlreadyExists(String userName);
	Boolean emailAlreadyExists(String email);

}
