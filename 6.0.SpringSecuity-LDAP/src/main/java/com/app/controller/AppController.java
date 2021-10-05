package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	@GetMapping("/data")
	public String CompanyData() {
		return "<h1>Company Data</h1>";
	}
}
