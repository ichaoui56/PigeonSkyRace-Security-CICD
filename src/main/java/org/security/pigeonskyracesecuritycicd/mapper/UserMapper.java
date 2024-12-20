package org.security.pigeonskyracesecuritycicd.mapper;

import org.security.pigeonskyracesecuritycicd.dto.user.RequestUserDTO;
import org.security.pigeonskyracesecuritycicd.dto.user.ResponseUserDTO;
import org.security.pigeonskyracesecuritycicd.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(config = GenericMapper.class)
public interface UserMapper extends GenericMapper<User, RequestUserDTO, ResponseUserDTO> {
}
