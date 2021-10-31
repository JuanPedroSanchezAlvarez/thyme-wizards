package com.thymewizards.domain.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thymewizards.domain.entity.User;

public interface IUserRepository extends PagingAndSortingRepository<User, UUID> {

}
