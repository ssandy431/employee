package com.app.emp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.emp.dto.LoginDTO;
import com.app.emp.repository.UserRepository;
import com.app.emp.response.JwtResponse;
import com.app.emp.service.impl.LoginServiceImpl;
import com.app.emp.util.JwtUtil;

//@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
	
	/*
	 * @Mock private UserDetailsService detailsService;
	 * 
	 * @Mock private AuthenticationManager manager;
	 * 
	 * @Mock private JwtUtil jwtUtil;
	 * 
	 * @InjectMocks private LoginServiceImpl service;
	 * 
	 * @Test public void testLogin() { LoginDTO dto = new LoginDTO();
	 * dto.setUsernameOrEmail("sandeep3"); dto.setPassword("passwords");
	 * 
	 * when(manager.authenticate(new
	 * UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(),
	 * dto.getPassword()))).thenReturn(null);
	 * when(detailsService.loadUserByUsername(dto.getUsernameOrEmail())).thenReturn(
	 * null); JwtResponse jwtResponse = service.login(dto);
	 * assertTrue(jwtResponse.isSuccess()); // doThrow(new
	 * BadCredentialsException("Username or password is incorrect")) //
	 * .when(manager) // .authenticate(new
	 * UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(),
	 * dto.getPassword()));
	 * 
	 * // assertThrows(BadCredentialsException.class, ()->manager.);
	 * 
	 * }
	 */
}
