package com.app.emp.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.emp.entity.User;
import com.app.emp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = repo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid Username or password : "+usernameOrEmail ));
		Set<GrantedAuthority> roles =user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
	}

}
