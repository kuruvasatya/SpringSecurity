package com.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class User {
	@Id
	int id;
	String userName;
	String password;
	@OneToMany(targetEntity = Authority.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="user_id", referencedColumnName="id")
	List<Authority> authority = new ArrayList<Authority>();

	public List<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(List<Authority> authority) {
		this.authority = authority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
