package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppContoller {
	
	@GetMapping("/")
	public String getHomePage() {
		return "Home  Page";
	}
	
	@GetMapping("/user")
	public String getUserPage() {
		return "User Page";
	}
	
	@GetMapping("/admin")
	public String getAdminPage() {
		return "Admin Page";
	}

}
