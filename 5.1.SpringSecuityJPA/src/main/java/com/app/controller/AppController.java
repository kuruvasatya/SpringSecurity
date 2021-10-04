package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.service.UserService;

@RestController
public class AppController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/")
	public String HomePage() {
		return "<h1>Home Page</h1>";
	}
	
	@GetMapping("/user")
	public String UserPage() {
		return "<h1>User Page</h1>";
	}
	
	@GetMapping("/admin")
	public String AdminPage() {
		return "<h1>Admin Page</h1>";
	}
	
	@PostMapping("/admin")
	public ResponseEntity<String> addUser(@RequestBody User user){
		return new  ResponseEntity<String>(service.addUser(user), HttpStatus.OK);
	}
}
