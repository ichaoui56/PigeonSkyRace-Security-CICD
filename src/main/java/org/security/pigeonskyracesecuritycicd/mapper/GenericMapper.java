package org.security.pigeonskyracesecuritycicd.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring",   unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface GenericMapper<ENTITY, REQUESTDTO, RESPONSEDTO> {
    ENTITY toEntity(REQUESTDTO requestdto);
    RESPONSEDTO toDTO(ENTITY entity);
    void updateEntityFromDTO(REQUESTDTO requestDTO, @MappingTarget ENTITY entity);
}
