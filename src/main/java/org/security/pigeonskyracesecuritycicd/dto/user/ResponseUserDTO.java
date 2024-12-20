package org.security.pigeonskyracesecuritycicd.dto.user;

import org.security.pigeonskyracesecuritycicd.model.entity.Role;

public record ResponseUserDTO(
        Long id,
        String username,
        String password,
        Role role
) {
}
