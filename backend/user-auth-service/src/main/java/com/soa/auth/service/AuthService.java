package com.soa.auth.service;
import com.soa.auth.dto.*;
import com.soa.auth.entity.*;
import com.soa.auth.repository.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class AuthService {
 private final UserRepository users; private final RoleRepository roles; private final PasswordEncoder encoder;
 private final AuthenticationManager authManager; private final JwtService jwt;
 public AuthService(UserRepository users,RoleRepository roles,PasswordEncoder encoder,AuthenticationManager authManager,JwtService jwt){
  this.users=users;this.roles=roles;this.encoder=encoder;this.authManager=authManager;this.jwt=jwt;
 }
 public void register(RegisterRequest r){
  String email=r.email().trim().toLowerCase();
  if(users.existsByEmailIgnoreCase(email)) throw new IllegalArgumentException("An account with this email already exists.");
  Role viewer=roles.findByName(RoleName.VIEWER).orElseThrow();
  User u=new User(email,encoder.encode(r.password()),r.fullName().trim()); u.getRoles().add(viewer); users.save(u);
 }
 public AuthResponse login(LoginRequest r){
  String email=r.email().trim().toLowerCase();
  authManager.authenticate(new UsernamePasswordAuthenticationToken(email,r.password()));
  User u=users.findByEmailIgnoreCase(email).orElseThrow();
  Set<String> rs=u.getRoles().stream().map(x->x.getName().name()).collect(Collectors.toSet());
  return new AuthResponse(jwt.generate(email,rs),"Bearer",3600,email,u.getFullName(),rs);
 }
}