package com.app.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GeneratorType;

@Entity
public class Authority {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String role;
	
	public Authority() {
		
	}
	
	public Authority(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
