package org.security.pigeonskyracesecuritycicd.mapper;

import org.security.pigeonskyracesecuritycicd.dto.role.RequestRoleDTO;
import org.security.pigeonskyracesecuritycicd.dto.role.ResponseRoleDTO;
import org.security.pigeonskyracesecuritycicd.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(config = GenericMapper.class)
public interface RoleMapper extends GenericMapper<Role, RequestRoleDTO, ResponseRoleDTO> {
}
