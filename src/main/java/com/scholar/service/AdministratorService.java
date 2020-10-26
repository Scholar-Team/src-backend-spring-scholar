package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scholar.dto.AdministratorDTO;
import com.scholar.mapper.AdministratorMapper;
import com.scholar.model.Administrator;
import com.scholar.repository.AdministratorRepository;
import com.scholar.repository.GroupRepository;
import com.scholar.request.AdministratorRequest;
import com.scholar.service.generic.AbstractPersonService;

@Service
public class AdministratorService 
	extends AbstractPersonService<Administrator, AdministratorDTO, AdministratorRequest> {

	private AdministratorRepository repository;
	private AdministratorMapper mapper;
	
	@Autowired
	public AdministratorService(AdministratorRepository repository, 
			AdministratorMapper mapper, GroupRepository groupRepository,
			PasswordEncoder encoder) {	
		super(repository, mapper, groupRepository, encoder);
		
		this.repository = repository;
		this.mapper = mapper;
	}
	
}
