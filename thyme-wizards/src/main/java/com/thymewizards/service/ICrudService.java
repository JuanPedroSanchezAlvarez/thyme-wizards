package com.thymewizards.service;

import java.util.List;

public interface ICrudService<T, ID> {

	List<T> findAll();
	T findById(ID id);
	T save(T object);
	Boolean deleteById(ID id);

}
