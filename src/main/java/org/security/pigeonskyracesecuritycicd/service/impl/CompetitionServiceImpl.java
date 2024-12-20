package org.security.pigeonskyracesecuritycicd.service.impl;

import lombok.AllArgsConstructor;
import org.security.pigeonskyracesecuritycicd.dto.competition.RequestCompetitionDTO;
import org.security.pigeonskyracesecuritycicd.dto.competition.ResponseCompetitionDTO;
import org.security.pigeonskyracesecuritycicd.exception.NotFoundException;
import org.security.pigeonskyracesecuritycicd.mapper.CompetitionMapper;
import org.security.pigeonskyracesecuritycicd.model.RoleType;
import org.security.pigeonskyracesecuritycicd.model.entity.Competition;
import org.security.pigeonskyracesecuritycicd.model.entity.User;
import org.security.pigeonskyracesecuritycicd.repository.CompetitionRepository;
import org.security.pigeonskyracesecuritycicd.repository.UserRepository;
import org.security.pigeonskyracesecuritycicd.service.CompetitionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;
    private final CompetitionMapper competitionMapper;

    @Transactional
    @Override
    public ResponseCompetitionDTO addCompetition(RequestCompetitionDTO requestCompetitionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userRepository.findByUsername(currentUser)
                .orElseThrow(() -> new NotFoundException("User not found with username: " + currentUser));

        if (!user.getRole().getRoleType().equals(RoleType.ROLE_ORGANIZER)) {
            throw new IllegalStateException("Only users with the ORGANIZER role can create competitions");
        }

        Competition competition = competitionMapper.toEntity(requestCompetitionDTO);
        competition.setUser(user);
        competition.setStatus(false);

        Competition savedCompetition = competitionRepository.save(competition);

        return competitionMapper.toDTO(savedCompetition);
    }

}
