package org.security.pigeonskyracesecuritycicd.mapper;

import org.security.pigeonskyracesecuritycicd.dto.competition.RequestCompetitionDTO;
import org.security.pigeonskyracesecuritycicd.dto.competition.ResponseCompetitionDTO;
import org.security.pigeonskyracesecuritycicd.model.entity.Competition;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

@Mapper(config = GenericMapper.class)
public interface CompetitionMapper extends GenericMapper<Competition, RequestCompetitionDTO, ResponseCompetitionDTO> {

    @Override
    Competition toEntity(RequestCompetitionDTO requestdto);

    @Override
    ResponseCompetitionDTO toDTO(Competition entity);
}
