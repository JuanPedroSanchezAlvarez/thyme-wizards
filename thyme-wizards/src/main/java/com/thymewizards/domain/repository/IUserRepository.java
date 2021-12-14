package com.thymewizards.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thymewizards.domain.entity.User;

public interface IUserRepository extends PagingAndSortingRepository<User, UUID> {

	Boolean existsByUserName(String userName);
	Boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);

}
