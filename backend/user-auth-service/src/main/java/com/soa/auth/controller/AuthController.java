package com.soa.auth.controller;
import com.soa.auth.dto.*;
import com.soa.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.Map;

@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AuthService service;
 public AuthController(AuthService service){this.service=service;}
 @PostMapping("/register") ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r){
  service.register(r); return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","User registered successfully. Default role: VIEWER."));
 }
 @PostMapping("/login") AuthResponse login(@Valid @RequestBody LoginRequest r){return service.login(r);}
 @GetMapping("/protected") Map<String,String> protectedEndpoint(){return Map.of("message","Protected User/Auth endpoint reached.");}
 @GetMapping("/admin")
 @PreAuthorize("hasRole('ADMIN')")
 public Map<String, String> adminEndpoint() {
    return Map.of("message", "Admin endpoint reached.");
 }
}