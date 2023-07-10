package com.app.emp.config;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
@Component
public class RequestEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
			System.out.println("isnide AuthenticationException");
			authException.printStackTrace();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
	
	
	@ExceptionHandler(value = {AccessDeniedException.class})
    public void commence(HttpServletRequest req, HttpServletResponse res,
                         AccessDeniedException ex) throws IOException {
		Map<String, Object> opMap = new HashMap<>();
		opMap.put("message", "You're not authorized to access this page.");
		opMap.put("isSuccess", false);
		res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setStatus(HttpStatus.FORBIDDEN.value());
        System.out.println("inside access denied "+(ex instanceof AccessDeniedException ));
        res.getWriter().write(convertObjectToJson(opMap));
    }
	@ExceptionHandler(value = {ExpiredJwtException.class})
	public void commence(HttpServletRequest req, HttpServletResponse res,
			ExpiredJwtException ex) throws IOException {
		Map<String, Object> opMap = new HashMap<>();
		opMap.put("message", "Session expired. Please login.");
		opMap.put("isSuccess", false);
		res.setContentType(MediaType.APPLICATION_JSON_VALUE);
		res.setStatus(HttpStatus.UNAUTHORIZED.value());
		
		res.getWriter().write(convertObjectToJson(opMap));
	}


    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
	
}
