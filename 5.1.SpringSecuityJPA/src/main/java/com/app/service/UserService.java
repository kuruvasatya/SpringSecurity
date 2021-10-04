package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.entity.Authority;
import com.app.entity.User;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public String addUser(User user)
	{
		user.getAuthority().add(new Authority("USER"));
		repository.save(user);
		return "User Added Succesfully";
	}
}
