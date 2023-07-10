package com.app.emp.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final String SECRET = "QWERTIOPPPP";
	
	
	public String generateToken(UserDetails userDetails)
	{
		Map<String,Object> claims = new HashMap<>();
		String token = generateToken(claims, userDetails.getUsername());
		return token;
	}
	
	public String getUsername(String token)
	{
		return getClaims(token, Claims::getSubject);
	}
	
	public Date getExiprationDate(String token)
	{
		return getExpirationDate(token);
	}

	public Date getExpirationDate(String token)
	{
		return getClaims(token, Claims::getExpiration);
	}
	
	private Boolean isTokenExpired(String token)
	{
		return getExpirationDate(token).before(new Date());
	}
	
	private <T> T getClaims(String token, Function<Claims,T> claimsResolver)
	{
		Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaims(String token)
	{
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}
	
	
	private String generateToken(Map<String, Object> claims, String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*60*60))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();
	}
	
	/*
	 * public Boolean validateToken(String token, UserDetails userDetails) { return
	 * isTokenValid(token, userDetails); }
	 */

	public Boolean isTokenValid(String token, UserDetails userDetails)
	{
		final String username = getUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
}
