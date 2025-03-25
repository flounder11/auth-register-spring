package com.fodi.auth_register.controllers;

import com.fodi.auth_register.dto.SigninRequest;
import com.fodi.auth_register.dto.SignupRequest;
import com.fodi.auth_register.models.Client;
import com.fodi.auth_register.repository.ClientRepository;
import com.fodi.auth_register.security.JwtCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    private ClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtCore jwtCore;

    @Autowired
    public SecurityController(ClientRepository clientRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtCore jwtCore) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtCore = jwtCore;
    }

    @PostMapping("/sign-up")
    ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        if (clientRepository.existsByPhoneNumber(signupRequest.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Этот номер телефона уже используется");
        }
        if (clientRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Данное имя пользователя уже занято");
        }
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        Client client = new Client();
        client.setUsername(signupRequest.getUsername());
        client.setPassword(hashedPassword);
        client.setPhoneNumber(signupRequest.getPhoneNumber());
        clientRepository.save(client);
        return ResponseEntity.ok("Successfully signed up");
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?>signin(@RequestBody SigninRequest signinRequest){
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Введите корректный логин или пароль", HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }
}
