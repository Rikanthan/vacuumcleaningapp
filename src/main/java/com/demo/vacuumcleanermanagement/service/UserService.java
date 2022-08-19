package com.demo.vacuumcleanermanagement.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.demo.vacuumcleanermanagement.model.User;

public interface UserService {
	String registerUser(User user);
	
	User loginUser(String email,String password);
}
