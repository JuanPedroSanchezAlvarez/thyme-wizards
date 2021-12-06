package com.thymewizards.service.impl;

import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thymewizards.domain.entity.User;
import com.thymewizards.domain.repository.IUserRepository;
import com.thymewizards.dto.UserDTO;
import com.thymewizards.exception.NotFoundException;
import com.thymewizards.mapper.IUserMapper;
import com.thymewizards.service.IUserService;
import com.thymewizards.util.LoggingUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements IUserService {

	private final IUserRepository repository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(IUserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
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
		return IUserMapper.INSTANCE.entityToDto(repository.findById(id).orElseThrow(() -> new NotFoundException(id)));
	}

	@Override
	@Transactional
	public UserDTO save(UserDTO dto) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		// return IUserMapper.INSTANCE.entityToDto(repository.save(IUserMapper.INSTANCE.dtoToEntity(dto)));
		User entity = IUserMapper.INSTANCE.dtoToEntity(dto);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		return IUserMapper.INSTANCE.entityToDto(repository.save(entity));
	}

	@Override
	@Transactional
	public void deleteById(UUID id) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		repository.deleteById(id);
	}

	@Override
	public Boolean userNameAlreadyExists(String userName) {
		return repository.existsByUserName(userName);
	}

	@Override
	public Boolean emailAlreadyExists(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	@Transactional
	public UserDTO update(UserDTO dto) {
		log.debug("LOG: Class: " + this.getClass().getName() + " --> Method: " + LoggingUtils.getCurrentMethodName());
		User entity = repository.findById(dto.getId()).orElseThrow(() -> new NotFoundException(dto.getId()));
		if (dto.getVersion() != entity.getVersion()) {
			throw new ObjectOptimisticLockingFailureException(User.class, entity.getId().toString());
		}
		return IUserMapper.INSTANCE.entityToDto(repository.save(IUserMapper.INSTANCE.dtoToEntity(dto)));
	}

}
