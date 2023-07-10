package com.app.emp.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
	
	@NotNull(message = "Username must not be empty or null.")
	private String username;
	@NotNull(message = "Email must not be empty or null.")
	@Email(message = "please enter valid email id.")
	private String email;
	@NotNull(message = "Password must not be empty or null.")
	private String password;
	
	@NotNull(message = "Role must not be empty or null.")
	private Set<RoleDTO> roles;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
}
