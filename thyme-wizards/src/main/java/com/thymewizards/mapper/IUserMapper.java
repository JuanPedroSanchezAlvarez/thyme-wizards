package com.thymewizards.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.thymewizards.domain.entity.User;
import com.thymewizards.dto.UserDTO;

@Mapper
public interface IUserMapper {

	IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

	UserDTO entityToDto(User entity);
	User dtoToEntity(UserDTO dto);

}
