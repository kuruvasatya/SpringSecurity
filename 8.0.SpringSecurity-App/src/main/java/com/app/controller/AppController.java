package com.app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.UserRepository;
import com.app.entity.Authority;
import com.app.entity.User;
import com.app.service.UserService;

@RestController
public class AppController {

	@Autowired
	UserService service;

	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody User user) {
		return new ResponseEntity<String>(service.addUser(user), HttpStatus.OK);
	}

	@GetMapping("/access/{userId}/{userRole}")
	public ResponseEntity<String> giveAccessToUser(@PathVariable("userId") int userId, @PathVariable("userRole") String role, Principal principal) {
		return new ResponseEntity<String>(service.giveAccess(userId, role, principal), HttpStatus.OK);
	}

	@GetMapping("/home")
	public String HomePage() {
		return "<h1>HOME PAGE</h1>";
	}

	@GetMapping("/welcome")
	public ResponseEntity<String> UserPage(Principal principal) {
		return new ResponseEntity<String>(service.showWelcomePage(principal), HttpStatus.OK);
	}

}
