package com.scholar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.AdministratorDTO;
import com.scholar.mapper.AdministratorMapper;
import com.scholar.model.Administrator;
import com.scholar.repository.AdministratorRepository;
import com.scholar.repository.RoleRepository;
import com.scholar.request.AdministratorRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.AbstractPersonService;

@Service
public class AdministratorService
	extends AbstractPersonService<Administrator, AdministratorDTO, AdministratorRequest> {

	private AdministratorRepository repository;
	private AdministratorMapper mapper;
	
	@Autowired
	public AdministratorService(
			AdministratorRepository repository, 
			AdministratorMapper mapper,
			RoleRepository roleRepository,
			PasswordEncoder encoder,
			AuthData authData) {	
		super(repository, mapper, roleRepository, 
				encoder, authData);
		
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	@Transactional
	public Optional<AdministratorDTO> save(AdministratorRequest request) {
		Administrator administrator = 
				configAdministrator(mapper.requestToModel(request));
		
		return Optional.of(mapper
				.modelToDTO(repository.saveAndFlush(administrator)));
	}
	
	private Administrator configAdministrator(Administrator administrator) {
		administrator = configModel(administrator);
		
		administrator.addRole(roleRepository
				.findById(1L).get());
		
		return administrator;
	}
	
}
