package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@GetMapping("/")
	public String HomePage() {
		return "<h1>Home Page </h1>";
	}

	@GetMapping("/user")
	public String UserPage() {
		return "<h1>User Page </h1>";
	}

	@GetMapping("/admin")
	public String AdminPage() {
		return "<h1>Admin Page </h1>";
	}

}
