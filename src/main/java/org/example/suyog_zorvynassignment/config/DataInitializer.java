package org.example.suyog_zorvynassignment.config;

import lombok.RequiredArgsConstructor;
import org.example.suyog_zorvynassignment.model.User;
import org.example.suyog_zorvynassignment.model.enums.Role;
import org.example.suyog_zorvynassignment.model.enums.UserStatus;
import org.example.suyog_zorvynassignment.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .name("Admin User")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .status(UserStatus.ACTIVE)
                    .build();
            userRepository.save(admin);
            
            User analyst = User.builder()
                    .name("Analyst User")
                    .email("analyst@example.com")
                    .password(passwordEncoder.encode("analyst123"))
                    .role(Role.ANALYST)
                    .status(UserStatus.ACTIVE)
                    .build();
            userRepository.save(analyst);
            
            User viewer = User.builder()
                    .name("Viewer User")
                    .email("viewer@example.com")
                    .password(passwordEncoder.encode("viewer123"))
                    .role(Role.VIEWER)
                    .status(UserStatus.ACTIVE)
                    .build();
            userRepository.save(viewer);
            
            System.out.println("Default users created: ");
            System.out.println("Admin -> admin@example.com / admin123");
            System.out.println("Analyst -> analyst@example.com / analyst123");
            System.out.println("Viewer -> viewer@example.com / viewer123");
        }
    }
}
