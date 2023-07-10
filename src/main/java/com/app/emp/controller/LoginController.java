package com.app.emp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.emp.dto.LoginDTO;
import com.app.emp.dto.UserDTO;
import com.app.emp.response.JwtResponse;
import com.app.emp.service.ILoginService;
import com.app.emp.service.conf.RedisServiceImpl;
import com.app.emp.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	private ILoginService service;
	
	@Autowired
	private RedisServiceImpl redisServiceImpl;
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping(value = "/register",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> register(@Valid @RequestBody UserDTO dto) throws Exception
	{
		JwtResponse response = service.register(dto);
		System.out.println("token :: "+response);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> signIn(@Valid @RequestBody LoginDTO dto)
	{
		return new ResponseEntity<>(service.login(dto),HttpStatus.OK);
	}
	
	@PostMapping(path = "/logout")
	public String logout(HttpServletResponse res, HttpServletRequest req)
	{
		
		try {
			String token = req.getHeader("Authorization");
			if(token != null && token.startsWith("Bearer "))
			{
				token = token.substring(7);
			}
			Date expirationTime = jwtUtil.getExpirationDate(token);
			System.out.println("exp time from token :: " +expirationTime);
			Date logoutTime = new Date();
			long diffInSecs = findDiff(expirationTime, logoutTime);
			System.out.println("log out time :: "+diffInSecs);
			redisServiceImpl.setValue(token, "logout", diffInSecs);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "You've been successfully logout!";
	}
	
	private static long findDiff(Date expTime, Date logoutTime)
	{
		long diffInTime = expTime.getTime() - logoutTime.getTime();
		long diffInHrs = (diffInTime /(1000*60*60)) %24;
		long diffInMins = (diffInTime/(1000 * 60)) % 60;
		long diffInSecs = (diffInTime/1000) % 60;
		diffInMins = (diffInHrs < 0 ? Math.abs(diffInMins) : diffInMins);
		diffInSecs = Math.abs(diffInSecs)+(diffInMins * 60);
		return diffInSecs;
	}
}
