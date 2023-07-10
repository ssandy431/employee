package com.app.emp.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {
	
	private boolean isSuccess;
	private String message;
	private String jwtToken;
	
	public JwtResponse(boolean isSuccess, String message, String jwtToken) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
		this.jwtToken = jwtToken;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
