package org.security.pigeonskyracesecuritycicd.service;

import org.security.pigeonskyracesecuritycicd.dto.competition.RequestCompetitionDTO;
import org.security.pigeonskyracesecuritycicd.dto.competition.ResponseCompetitionDTO;
import org.security.pigeonskyracesecuritycicd.dto.user.RequestUserDTO;

public interface CompetitionService {
    ResponseCompetitionDTO addCompetition(RequestCompetitionDTO requestCompetitionDTO);
}
