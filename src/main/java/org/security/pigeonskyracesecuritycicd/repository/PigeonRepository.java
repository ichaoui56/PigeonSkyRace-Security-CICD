package org.security.pigeonskyracesecuritycicd.repository;

import org.security.pigeonskyracesecuritycicd.model.entity.Pigeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PigeonRepository extends JpaRepository<Pigeon, Long> {
    Optional<Pigeon> findByRingNumber(String ringNumber);
}
