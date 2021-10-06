package com.app.service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.entity.Authority;
import com.app.entity.User;

@Service
public class UserService {

	private final String DEFAULT_ROLE = "USER";
	private final String[] MODERATOR_PERMISSIONS = { "ADMIN" };
	private final String[] ADMIN_PERMISSIONS = { "ADMIN", "MODERATOR" };

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UserRepository repo;

	public String addUser(User user) {
		System.out.println("I am here");
		user.getAuthority().add(new Authority(DEFAULT_ROLE));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(user);
		return "Welcome to the group Mr." + user.getUserName();
	}

	public String giveAccess(int uid, String role, Principal principal) {
		Optional<User> dummyUser = repo.findById(uid);
		if (!dummyUser.isPresent())
			return "User with uid " + uid + " is not found!";

		User user = dummyUser.get();
		List<String> currentLoggedInUserPermissions = getPermissionsByCurrentUser(principal);
		
		if (currentLoggedInUserPermissions.contains(role)) {
			user.getAuthority().add(new Authority(role));
			repo.save(user);
			return "User " + user.getUserName() + " is in now " + role;
		} else {
			return "Role can not be assigned by " + principal.getName();
		}
	}

	public List<String> getPermissionsByCurrentUser(Principal principal) {
		List<String> roles = getCurrentLoggedInUser(principal).getAuthority().stream()
									.map((authority) -> authority.getRole())
									.collect(Collectors.toList());
		if(roles.contains("ADMIN"))
			return Arrays.asList(ADMIN_PERMISSIONS);
		else if(roles.contains("MODERATOR"))
			return Arrays.asList(MODERATOR_PERMISSIONS);
		else
			return Collections.emptyList();
	}

	public User getCurrentLoggedInUser(Principal principal) {
		return repo.findUserByUserName(principal.getName()).get();
	}

	public String showWelcomePage(Principal principal) {
		return "Welcome "  + principal.getName();
	}

}
