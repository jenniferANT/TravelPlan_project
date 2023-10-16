package com.app.travelplan.config;

import com.app.travelplan.model.entity.Image;
import com.app.travelplan.model.entity.Role;
import com.app.travelplan.model.entity.User;
import com.app.travelplan.repository.ImageRepository;
import com.app.travelplan.repository.RoleRepository;
import com.app.travelplan.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class RoleConfig {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageRepository imageRepository;


    public RoleConfig(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ImageRepository imageRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.imageRepository = imageRepository;
    }

    @Bean
    public void accountConfig() {
        if(!roleRepository.existsRoleByName("ROLE_USER") && !roleRepository.existsRoleByName("ROLE_ADMIN")) {
            var userRole = Role.builder()
                    .id(1l)
                    .name("ROLE_USER")
                    .build();
            var adminRole = Role.builder()
                    .id(2l)
                    .name("ROLE_ADMIN")
                    .build();
            roleRepository.saveAll(List.of(userRole, adminRole));
        }

        if(!userRepository.existsUserByUsername("admin")) {
            var admin = User.builder()
                    .id(1l)
                    .name("Admin")
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(roleRepository.findByName("ROLE_ADMIN").get())
                    .build();
            userRepository.save(admin);
        }
    }
}
