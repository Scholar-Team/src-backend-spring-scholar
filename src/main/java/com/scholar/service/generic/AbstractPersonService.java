package com.scholar.service.generic;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.scholar.dto.PersonDTO;
import com.scholar.mapper.BaseMapper;
import com.scholar.mapper.FileMapper;
import com.scholar.model.File;
import com.scholar.model.Person;
import com.scholar.repository.RoleRepository;
import com.scholar.request.PersonRequest;
import com.scholar.request.RoleRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.FileService;

public abstract class AbstractPersonService<
	M extends Person, 
	D extends PersonDTO, 
	R extends PersonRequest>
	extends BaseService<M, D, R> {

	protected RoleRepository roleRepository;
	protected PasswordEncoder encoder;
	protected ObjectMapper objectMapper;
	protected FileMapper fileMapper;
	protected FileService fileService;
	
	private Set<RoleRequest> userRoles;
	
	public AbstractPersonService(
			JpaRepository<M, Long> repository,
			BaseMapper<M, D, R> mapper, 
			RoleRepository roleRepository,
			PasswordEncoder encoder,
			AuthData authData,
			FileMapper fileMapper,
			FileService fileService) {
		super(repository, mapper, authData);
		
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.fileMapper = fileMapper;
		this.fileService = fileService;
		
		this.userRoles = Sets.newHashSet(new RoleRequest[] {
			RoleRequest.builder().id(2L).build()
		});
	}
	
	@Override
	@Transactional
	public Optional<D> save(R request) {
		M model = configModel(mapper.requestToModel(request));
		
		return Optional.of(mapper
				.modelToDTO(repository.saveAndFlush(model)));
	}
	
	protected M configModel(M model) {
		model.setPassword(encoder.encode(model.getPassword()));
				
		userRoles.forEach(x -> {
			model.addRole(roleRepository.findById(x.getId()).get());
		});
		
		return model;
	}
	
	protected File configFile(R request) {
		File file = fileMapper.dtoToModel(fileService
						.save(request.getFile()).get());
		
		return file;
	}

}
