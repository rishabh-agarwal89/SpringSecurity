package com.ms.practice.shoppingWithJwt.jjwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String SECRET_KEY = "my-super-secret-key-my-super-12345";
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().subject(userDetails.getUsername()).
				issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+1000*60*10)).
				signWith(getSigningKey()).compact();
	}
	
	public String extractUsername(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
	}
	
	public boolean isTokenValid(String token,UserDetails userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
	}
	
	public boolean isTokenExpired(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
	}
	
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

}
