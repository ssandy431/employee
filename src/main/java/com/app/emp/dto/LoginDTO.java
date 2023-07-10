package com.app.emp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {
	
	@NotBlank(message = "Username or email is required.")
	@NotNull(message = "Username or email is required.")
	private String usernameOrEmail;
	@NotBlank(message = "password is required.")
	@NotNull(message = "password is required.")
	private String password;
	
	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}
	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
