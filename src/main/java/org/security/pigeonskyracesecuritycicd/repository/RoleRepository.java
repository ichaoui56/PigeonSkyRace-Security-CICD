package org.security.pigeonskyracesecuritycicd.repository;

import org.security.pigeonskyracesecuritycicd.model.RoleType;
import org.security.pigeonskyracesecuritycicd.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
