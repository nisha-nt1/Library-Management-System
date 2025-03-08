package com.liberary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liberary.Entity.User;
import com.liberary.dto.LoginRequestDto;
import com.liberary.dto.LoginResponseDto;
import com.liberary.dto.RegisterRequestDto;
import com.liberary.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/registernormaluser")
	public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDto registerRequestDto){
		
		return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
		return ResponseEntity.ok(authenticationService.login(loginRequestDto));
	}
	
	

}
