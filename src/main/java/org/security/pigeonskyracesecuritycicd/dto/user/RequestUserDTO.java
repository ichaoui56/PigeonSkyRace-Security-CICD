package org.security.pigeonskyracesecuritycicd.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.security.pigeonskyracesecuritycicd.model.RoleType;

public record RequestUserDTO(
        @NotBlank(message = "Username must be entered") String username,
        @NotBlank(message = "Please provide a password") String password
) {
}
