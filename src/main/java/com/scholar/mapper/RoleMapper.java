package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.RoleDTO;
import com.scholar.model.Role;
import com.scholar.request.RoleRequest;

@Component
public class RoleMapper extends BaseMapper<Role, RoleDTO, RoleRequest> {

	public RoleMapper() {
		super(Role.class, RoleDTO.class, RoleRequest.class);
	}
}
