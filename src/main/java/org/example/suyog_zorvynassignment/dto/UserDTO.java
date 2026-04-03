package org.example.suyog_zorvynassignment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.suyog_zorvynassignment.model.enums.Role;
import org.example.suyog_zorvynassignment.model.enums.UserStatus;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private Role role;
    private UserStatus status;
}
