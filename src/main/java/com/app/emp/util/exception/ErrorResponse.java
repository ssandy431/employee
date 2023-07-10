package com.app.emp.util.exception;

import java.util.Date;


public class ErrorResponse {
	
	private Date timestamp;
	private String message;
	private String url;
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getUrl() {
		return url;
	}
	public ErrorResponse(Date timestamp, String message, String url) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.url = url;
	}
	public ErrorResponse() {
		super();
	}
}
