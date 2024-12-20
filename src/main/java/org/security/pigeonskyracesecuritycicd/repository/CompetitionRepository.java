package org.security.pigeonskyracesecuritycicd.repository;

import org.security.pigeonskyracesecuritycicd.model.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
