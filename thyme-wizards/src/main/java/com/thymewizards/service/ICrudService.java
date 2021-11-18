package com.thymewizards.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrudService<T, ID> {

	Page<T> findAll(Pageable pageable);
	T findById(ID id);
	T save(T object);
	Boolean deleteById(ID id);
	T update(T object);

}
