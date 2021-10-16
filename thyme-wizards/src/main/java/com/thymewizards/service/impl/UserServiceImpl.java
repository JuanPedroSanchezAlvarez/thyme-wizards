package com.thymewizards.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thymewizards.domain.entity.User;
import com.thymewizards.domain.repository.IUserRepository;
import com.thymewizards.dto.UserDTO;
import com.thymewizards.mapper.IUserMapper;
import com.thymewizards.service.IUserService;
import com.thymewizards.util.LoggingUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements IUserService {

	private final IUserRepository repository;

	public UserServiceImpl(IUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<UserDTO> findAll() {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		List<User> listEntity = (List<User>) repository.findAll();
		List<UserDTO> listDTO = new ArrayList<>();
		listEntity.forEach((entity) -> { listDTO.add(IUserMapper.INSTANCE.entityToDto(entity)); });
		return listDTO;
	}

	@Override
	public UserDTO findById(UUID id) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		return IUserMapper.INSTANCE.entityToDto(repository.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public UserDTO save(UserDTO o) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		return IUserMapper.INSTANCE.entityToDto(repository.save(IUserMapper.INSTANCE.dtoToEntity(o)));
	}

	@Override
	@Transactional
	public Boolean deleteById(UUID id) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

}
