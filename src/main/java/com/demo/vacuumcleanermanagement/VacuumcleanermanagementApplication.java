package com.demo.vacuumcleanermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@EntityScan(basePackages = {"com.demo"})
public class VacuumcleanermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacuumcleanermanagementApplication.class, args);
	}
}
