package com.thymewizards.service.impl;

import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<UserDTO> findAll(Pageable pageable) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		Page<UserDTO> pageDTO = repository.findAll(pageable).map(new Function<User, UserDTO>() {
		    @Override
		    public UserDTO apply(User entity) { return IUserMapper.INSTANCE.entityToDto(entity); }
		});
		return pageDTO;
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

	@Override
	public Boolean userNameAlreadyExists(String userName) {
		return repository.existsByUserName(userName);
	}

	@Override
	public Boolean emailAlreadyExists(String email) {
		return repository.existsByEmail(email);
	}

}
