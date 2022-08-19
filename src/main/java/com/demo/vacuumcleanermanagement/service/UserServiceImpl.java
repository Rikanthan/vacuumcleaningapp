package com.demo.vacuumcleanermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.vacuumcleanermanagement.dao.UserRepository;
import com.demo.vacuumcleanermanagement.model.User;


@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	@Autowired
	UserRepository repository;
	@Override
	public String registerUser(User user) {
		if(!validateEmail(user.getEmail())) {
			return "Please provide valid email";
		}
		if(!validatePhoneNumber(String.valueOf(user.getPhoneNo()))){
			return "Please provide valid phonenumber";
		}
		if(!validatePassword(user.getPassword())) {
			return "Please provide strong password";
		}
		//user.setPassword(bCrypt.encode(user.getPassword()));
		repository.save(user);
		return "User created successfully";
	}

	@Override
	public User loginUser(String email,String password) {
		if(email != null && password != null) {
			Optional<User> user = repository.findByEmailAndPassword(email, password);
			if(user.isPresent())
			{
				return user.get();
			}
		}
		return null;
	}
	
	public List<User> findAllUser(){
		return repository.findAll();
	}
	
	public boolean validateEmail(String email) {
	   String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	   Pattern pattern = Pattern.compile(regex);
	   Matcher matcher = pattern.matcher(email);
	   return matcher.matches(); 
	}
	
	public boolean validatePhoneNumber(String phone) {
		   String regex = "(0/94)?[7-9][0-9]{9}";
		   Pattern pattern = Pattern.compile(regex);
		   Matcher matcher = pattern.matcher(phone);
		   return matcher.matches(); 
		}
	
	public boolean validatePassword(String password) {
		   String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
		   Pattern pattern = Pattern.compile(regex);
		   Matcher matcher = pattern.matcher(password);
		   return matcher.matches(); 
		}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByName(username);
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), new ArrayList<>());
	}
}
