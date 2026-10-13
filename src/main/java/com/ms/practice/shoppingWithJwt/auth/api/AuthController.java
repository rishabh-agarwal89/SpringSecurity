package com.ms.practice.shoppingWithJwt.auth.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.practice.shoppingWithJwt.dto.LoginRequest;
import com.ms.practice.shoppingWithJwt.jjwt.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	
	
	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
			UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}



	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest request){
		this.authenticationManager.authenticate(new 
				UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user= this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtService.generateToken(user);
		return ResponseEntity.ok(token);
	}

}
