package com.soa.auth.service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
@Service
public class JwtService {
 private final SecretKey key;
 public JwtService(@Value("${security.jwt.secret}") String secret){
  if(secret.length()<32) throw new IllegalArgumentException("JWT secret must be at least 32 characters");
  key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
 }
 public String generate(String email,Set<String> roles){
  var now=Instant.now();
  return Jwts.builder().subject(email).claim("roles",roles).issuedAt(Date.from(now))
   .expiration(Date.from(now.plusSeconds(3600))).signWith(key).compact();
 }
}