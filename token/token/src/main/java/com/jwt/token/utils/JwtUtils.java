package com.jwt.token.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.jwt.token.Entity.User;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.security.SignatureAlgorithm;

@Component
public class JwtUtils {

	private String secret = "qwertyuiopkjjhgfdsazxcvdzdbnwery";

	public String generateJwt(User user) {
		try {
			String jwt = Jwts.builder().header().and().claim("id", user.getId())
					.claim("issuedAt", new Date(System.currentTimeMillis()))
					.claim("expiryAt", new Date((System.currentTimeMillis()) + 3600000))
					.claim("name", user.getUsername()).claim("email", user.getEmail())
					.claim("ph_Num", user.getPhoneNumber()).claim("gender", user.getGender()).signWith(getSigningKey())
					.compact();
			return jwt;
		} catch (Exception e) {
			return e.toString();
		}
	}

	private Key getSigningKey() {
		
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_16);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String verifyToken(String token) {
		try {

			Jwts.parser().verifyWith((SecretKey) getSigningKey()).build().parseSignedClaims(token);
			return "success...";

		} catch (JwtException e) {

			return e.toString();
		}
	}

	public String takeData(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).build();
			
			// Verify and decode the JWT
			DecodedJWT jwt = verifier.verify(token);
			
			System.out.println("Token is valid: " + jwt.getToken());
			System.out.println("Name: " + jwt.getClaim("name").asString());
			System.out.println("Email: " + jwt.getClaim("email").asString());
		} catch (Exception e) {
			// Handle the exception
			System.err.println("Invalid Token: " + e.getMessage());
		}
		return null;
	}

}
