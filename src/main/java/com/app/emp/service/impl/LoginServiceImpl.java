package com.app.emp.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.emp.dto.LoginDTO;
import com.app.emp.dto.UserDTO;
import com.app.emp.entity.User;
import com.app.emp.repository.UserRepository;
import com.app.emp.response.JwtResponse;
import com.app.emp.service.ILoginService;
import com.app.emp.util.JwtUtil;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserDetailsService  detailsService; 
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtUtil jwtUtil;
	

	@Override
	public JwtResponse login(LoginDTO dto) throws BadCredentialsException {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(), dto.getPassword()));
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new BadCredentialsException("Invalid Username or Password");
		}
		UserDetails userDetails = detailsService.loadUserByUsername(dto.getUsernameOrEmail());
		System.out.println(userDetails.getAuthorities());
		return new  JwtResponse(true, "You've been registered successfully", jwtUtil.generateToken(userDetails));
	}

	@Override
	public JwtResponse register(UserDTO dto) throws Exception {
		try {
			
			Optional<User> findByEmailUser= repository.findByEmail(dto.getEmail());
			Optional<User> findByUsernameUser= repository.findByUsername(dto.getUsername());
			if(findByEmailUser.isPresent())
			{
				return new  JwtResponse(false, "Email id: "+dto.getEmail()+" already exists in our database.", null);
			}
			else if(findByUsernameUser.isPresent())
			{
				return new  JwtResponse(false, "Username: "+dto.getUsername()+" already exists in our database.", null);
			}
			else {
				dto.setPassword(passwordEncoder.encode(dto.getPassword()));
				repository.save(mapper.map(dto, User.class));
			}
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new BadCredentialsException("Invalid Username or Password");
		}
		UserDetails userDetails = detailsService.loadUserByUsername(dto.getEmail());
		return new  JwtResponse(true, "You've been registered successfully", jwtUtil.generateToken(userDetails));
	}
}
