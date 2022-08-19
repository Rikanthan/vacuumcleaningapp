package com.demo.vacuumcleanermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.vacuumcleanermanagement.model.AuthRequest;
import com.demo.vacuumcleanermanagement.model.User;
import com.demo.vacuumcleanermanagement.service.UserServiceImpl;
import com.demo.vacuumcleanermanagement.util.JwtUtil;

@RestController
public class UserController {
	@Autowired
	UserServiceImpl service;
	@Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
	@PostMapping
	public String createUser(@RequestBody User user) {
		return service.registerUser(user);
	}
	@GetMapping
	public List<User> findAll(){
		return service.findAllUser();
	}
	@GetMapping(value = "/login")
	public User login(@RequestParam String email,@RequestParam String password) {
		return service.loginUser(email, password);
	}
	

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to javatechie !!";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            System.out.print(authRequest.getUsername());
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        System.out.print(authRequest.getUsername());
        return jwtUtil.generateToken(authRequest.getUsername());
    }
	
}
