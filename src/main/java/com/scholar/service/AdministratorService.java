package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.AdministratorDTO;
import com.scholar.mapper.AdministratorMapper;
import com.scholar.model.Administrator;
import com.scholar.repository.AdministratorRepository;
import com.scholar.request.AdministratorRequest;

@Service
public class AdministratorService 
	extends BaseService<Administrator, AdministratorDTO, AdministratorRequest> {

	private AdministratorRepository repository;
	
	@Autowired
	public AdministratorService(AdministratorRepository repository, AdministratorMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
}
