package com.app.emp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.emp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User>  findByEmail(String email);
	Optional<User>  findByUsername(String username);
	Optional<User> findByUsernameOrEmail(String username,String email);	
}
