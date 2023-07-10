package com.app.emp.service;

import com.app.emp.dto.LoginDTO;
import com.app.emp.dto.UserDTO;
import com.app.emp.response.JwtResponse;

public interface ILoginService {
	
	public JwtResponse login(LoginDTO dto);
	
	public JwtResponse register(UserDTO dto) throws Exception;

}
