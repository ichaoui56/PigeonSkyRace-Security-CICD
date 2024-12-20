package org.security.pigeonskyracesecuritycicd.mapper;

import org.security.pigeonskyracesecuritycicd.dto.pigeon.RequestPigeonDTO;
import org.security.pigeonskyracesecuritycicd.dto.pigeon.ResponsePigeonDTO;
import org.security.pigeonskyracesecuritycicd.model.entity.Pigeon;
import org.mapstruct.Mapper;

@Mapper(config = GenericMapper.class)
public interface PigeonMapper extends GenericMapper<Pigeon, RequestPigeonDTO, ResponsePigeonDTO> {
}
