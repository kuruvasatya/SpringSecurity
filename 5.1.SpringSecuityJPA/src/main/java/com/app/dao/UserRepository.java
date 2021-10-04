package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findUserByUserName(String username);


}
