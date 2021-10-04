package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.entity.MyUserDetails;
import com.app.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repo.findUserByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException("user : " + username + " is not found"));
		return user.map(MyUserDetails::new).get();

	}

}
