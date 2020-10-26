package com.scholar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.GroupDTO;
import com.scholar.mapper.GroupMapper;
import com.scholar.model.Group;
import com.scholar.model.Permission;
import com.scholar.repository.GroupRepository;
import com.scholar.repository.PermissionRepository;
import com.scholar.request.GroupRequest;
import com.scholar.service.generic.BaseService;

@Service
public class GroupService extends BaseService<Group, GroupDTO, GroupRequest> {

	private GroupRepository repository;
	private GroupMapper mapper;
	
	private PermissionRepository permissionRepository;
	
	@Autowired
	public GroupService(GroupRepository repository, GroupMapper mapper,
			PermissionRepository permissionRepository) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.permissionRepository = permissionRepository;
	}
	
	@Override
	public Optional<GroupDTO> save(GroupRequest request) {
		Group group = configGroup(mapper.requestToModel(request));

		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(group)));
	}
	
	private Group configGroup(Group group) {
		List<Permission> permissions = new ArrayList<>();
		
		for(Permission permission : group.getPermissions()) {
			permissions.add(permissionRepository.findById(permission.getId()).get());
		}
		
		group.setPermissions(permissions);
		
		return group;
	}
	
}
