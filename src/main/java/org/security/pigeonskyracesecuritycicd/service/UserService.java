package org.security.pigeonskyracesecuritycicd.service;

import jakarta.transaction.Transactional;
import org.security.pigeonskyracesecuritycicd.dto.user.RequestUserDTO;
import org.security.pigeonskyracesecuritycicd.model.entity.User;

import java.util.Optional;

public interface UserService {
    User register(RequestUserDTO userDTO);

    User changeRole(String username, String newRole);

    Optional<User> findByUsername(String name);
}
