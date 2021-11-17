package com.example.security;

import com.example.security.domain.Role;
import com.example.security.domain.User;
import com.example.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args->{
			userService.saveRole(new Role(0,"ROLE_USER"));
			userService.saveRole(new Role(0,"ROLE_ADMIN"));
			userService.saveRole(new Role(0,"ROLE_MANAGER"));
			userService.saveRole(new Role(0,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(0, "Test User", "user", "1234", new ArrayList<>()));
			userService.saveUser(new User(0, "Test Admin", "admin", "1234", new ArrayList<>()));
			userService.saveUser(new User(0, "Test Manager", "manager", "1234", new ArrayList<>()));
			userService.saveUser(new User(0, "Test Super Admin", "superadmin", "1234", new ArrayList<>()));

			userService.addRoleToUser("user", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_ADMIN");
			userService.addRoleToUser("manager", "ROLE_MANAGER");
			userService.addRoleToUser("superadmin", "ROLE_USER");
			userService.addRoleToUser("superadmin", "ROLE_ADMIN");
			userService.addRoleToUser("superadmin", "ROLE_MANAGER");
			userService.addRoleToUser("superadmin", "ROLE_SUPER_ADMIN");

		};
	}

}
