package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.PermissionDTO;
import com.scholar.model.Permission;
import com.scholar.request.PermissionRequest;

@Component
public class PermissionMapper extends BaseMapper<Permission, PermissionDTO, PermissionRequest> {

	public PermissionMapper() {
		super(Permission.class, PermissionDTO.class, PermissionRequest.class);
	}
}

