package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.PermissionDTO;
import com.scholar.mapper.PermissionMapper;
import com.scholar.model.Permission;
import com.scholar.repository.PermissionRepository;
import com.scholar.request.PermissionRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class PermissionService extends BaseService<Permission, PermissionDTO, PermissionRequest> {

	private PermissionRepository repository;
	private PermissionMapper mapper;
	
	@Autowired
	public PermissionService(
			PermissionRepository repository,
			PermissionMapper mapper,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
	}
	
}
