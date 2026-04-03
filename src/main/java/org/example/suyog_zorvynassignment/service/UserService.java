package org.example.suyog_zorvynassignment.service;

import lombok.RequiredArgsConstructor;
import org.example.suyog_zorvynassignment.dto.UserDTO;
import org.example.suyog_zorvynassignment.model.User;
import org.example.suyog_zorvynassignment.model.enums.Role;
import org.example.suyog_zorvynassignment.model.enums.UserStatus;
import org.example.suyog_zorvynassignment.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(userDTO.getRole() != null ? userDTO.getRole() : Role.VIEWER)
                .status(UserStatus.ACTIVE)
                .build();
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User user = getUserById(id);
        
        user.setName(userDTO.getName());
        // Only update password if provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if (userDTO.getRole() != null) {
            user.setRole(userDTO.getRole());
        }
        
        return userRepository.save(user);
    }

    public void activateDeactivateUser(Long id, UserStatus status) {
        User user = getUserById(id);
        user.setStatus(status);
        userRepository.save(user);
    }
}
