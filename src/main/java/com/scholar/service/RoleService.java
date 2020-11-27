package com.scholar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.RoleDTO;
import com.scholar.mapper.RoleMapper;
import com.scholar.model.Role;
import com.scholar.repository.PermissionRepository;
import com.scholar.repository.RoleRepository;
import com.scholar.request.RoleRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class RoleService extends BaseService<Role, RoleDTO, RoleRequest> {

	private RoleRepository repository;
	private RoleMapper mapper;
	
	private PermissionRepository permissionRepository;
	
	@Autowired
	public RoleService(
			RoleRepository repository,
			RoleMapper mapper,
			PermissionRepository permissionRepository,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.permissionRepository = permissionRepository;
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		Role role = repository.findById(id).get();
				
		role.getPersons().forEach(x -> {
			x.getRoles().remove(role);
		});
		
		repository.delete(role);
	}
	
	@Override
	@Transactional
	public Optional<RoleDTO> save(RoleRequest request) {
		Role role = configRole(mapper.requestToModel(request), request);

		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(role)));
	}
	
	private Role configRole(Role role, RoleRequest request) {
		request.getPermissions().forEach(x -> {
			role.addPermission(permissionRepository
					.findById(x.getId()).get());
		});
		
		return role;
	}
	
}
