package com.fodi.auth_register;

import com.fodi.auth_register.models.Client;
import com.fodi.auth_register.repository.ClientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthRegisterApplication.class, args);

	}
}
