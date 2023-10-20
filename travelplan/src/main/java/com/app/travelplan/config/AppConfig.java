package com.app.travelplan.config;

import com.app.travelplan.model.entity.Category;
import com.app.travelplan.model.entity.Role;
import com.app.travelplan.model.entity.User;
import com.app.travelplan.repository.CategoryRepository;
import com.app.travelplan.repository.ImageRepository;
import com.app.travelplan.repository.RoleRepository;
import com.app.travelplan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

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
                    .email("admin@gmail.com")
                    .role(roleRepository.findByName("ROLE_ADMIN").get())
                    .build();
            userRepository.save(admin);
        }

        if(!categoryRepository.existsByName("like")) {
            var like = Category.builder()
                    .id(1l)
                    .name("like")
                    .category(null)
                    .build();
            categoryRepository.save(like);
        }
        if(!categoryRepository.existsByName("area")) {
            var area = Category.builder()
                    .id(2l)
                    .name("area")
                    .category(null)
                    .build();
            categoryRepository.save(area);
        }
    }
}
