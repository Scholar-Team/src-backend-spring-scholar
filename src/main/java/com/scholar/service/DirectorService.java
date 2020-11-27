package com.scholar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.DirectorDTO;
import com.scholar.mapper.DirectorMapper;
import com.scholar.model.Director;
import com.scholar.repository.DirectorRepository;
import com.scholar.repository.RoleRepository;
import com.scholar.repository.SchoolRepository;
import com.scholar.request.DirectorRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.AbstractPersonService;

@Service
public class DirectorService
	extends AbstractPersonService<Director, DirectorDTO, DirectorRequest> {

	private DirectorRepository repository;
	private DirectorMapper mapper;
	
	private SchoolRepository schoolRepository;
	
	@Autowired
	public DirectorService(
			DirectorRepository repository,
			DirectorMapper mapper,
			RoleRepository roleRepository,
			PasswordEncoder encoder,
			SchoolRepository schoolRepository,
			AuthData authData) {	
		super(repository, mapper, roleRepository, 
				encoder, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.schoolRepository = schoolRepository;
	}
	
	@Override
	@Transactional
	public Optional<DirectorDTO> save(DirectorRequest request) {
		Director director = configDirector(mapper.requestToModel(request));
		
		return Optional.of(mapper
				.modelToDTO(repository.saveAndFlush(director)));
	}
	
	private Director configDirector(Director director) {
		director = configModel(director);
		
		director.addRole(roleRepository
				.findById(5L).get());
		
		director.setSchool(schoolRepository
				.findById(director.getSchool().getId()).get());
		
		return director;
	}

}
