package com.thymewizards.domain.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.thymewizards.domain.entity.User;

public interface IUserRepository extends CrudRepository<User, UUID> {

}
