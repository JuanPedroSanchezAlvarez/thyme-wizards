package com.thymewizards.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thymewizards.domain.entity.User;
import com.thymewizards.domain.repository.IUserRepository;
import com.thymewizards.util.ApplicationUserDetails;

@Transactional(readOnly = true)
@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	private final IUserRepository userRepository;

	@Autowired
    public DatabaseUserDetailsService(IUserRepository userRepository) {
    	this.userRepository = userRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s could not be found", username)));
		return new ApplicationUserDetails(user);
	}

}
