package com.liberary.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.liberary.Entity.User;
import com.liberary.JWT.JwtService;
import com.liberary.dto.LoginRequestDto;
import com.liberary.dto.LoginResponseDto;
import com.liberary.dto.RegisterRequestDto;
import com.liberary.repository.UserRepo;

@ Service
public class AuthenticationService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	public User registerNormalUser(RegisterRequestDto registerRequestDto) {
		if(userRepo.findByUsername(registerRequestDto.getUsername()).isPresent()) {
			throw new RuntimeException("User already registered");
		}
		Set<String> roles = new HashSet<String>();
		roles.add("ROLE_USER");
		
		User user = new User();
		user.setUsername(registerRequestDto.getUsername());
		user.setEmail(registerRequestDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
		user.setRoles(roles);
		
		return userRepo.save(user);
		
	}
	
	public User registerAdminUser(RegisterRequestDto registerRequestDto) {
		
		if(userRepo.findByUsername(registerRequestDto.getUsername()).isPresent()) {
			throw new RuntimeException("User already registeded");
		}
		
		Set<String> roles = new HashSet<String>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		
		User user = new User();
		user.setUsername(registerRequestDto.getUsername());
		user.setEmail(registerRequestDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
		user.setRoles(roles);
		
		return userRepo.save(user);
	}
	
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
				);
		
		User user = userRepo.findByUsername(loginRequestDto.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));
		
		String token = jwtService.generateToken(user);
		
		return LoginResponseDto.builder()
				.token(token)
				.username(user.getUsername())
				.roles(user.getRoles())
				.build();
	}

}
