package com.soa.auth.service;

import com.soa.auth.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        var user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        var authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(
                        "ROLE_" + role.getName().name()))
                .toList();

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .disabled(!user.isActive())
                .authorities(authorities)
                .build();
    }
}