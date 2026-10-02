package com.soa.auth.dto;
import java.util.Set;
public record AuthResponse(String token,String tokenType,long expiresInSeconds,String email,String fullName,Set<String> roles) {}