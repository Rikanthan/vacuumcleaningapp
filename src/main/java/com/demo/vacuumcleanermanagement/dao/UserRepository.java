package com.demo.vacuumcleanermanagement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.vacuumcleanermanagement.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	Optional<User> findByEmailAndPassword(String email,String password);
	
	User findByName(String name);
}
