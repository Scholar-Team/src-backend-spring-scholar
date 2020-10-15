package com.scholar.service;

import java.util.Optional;

import javax.transaction.Transactional;

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
	private AdministratorMapper mapper;
	
	public AdministratorService(AdministratorRepository repository, 
			AdministratorMapper mapper) {	
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public Optional<AdministratorDTO> save(AdministratorRequest request) {
		Administrator admin = mapper.requestToModel(request);
		
		admin.getTelephones()
			.stream()
			.forEach(x -> x.setPerson(admin));
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(admin)));
	}
	
}
