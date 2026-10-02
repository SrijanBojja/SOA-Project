package com.soa.auth.config;

import com.soa.auth.entity.Role;
import com.soa.auth.entity.RoleName;
import com.soa.auth.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository repo) {
        return args -> {
            for (RoleName roleName : RoleName.values()) {
                repo.findByName(roleName)
                        .orElseGet(() -> repo.save(new Role(roleName)));
            }
        };
    }
}