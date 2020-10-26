package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scholar.dto.DirectorDTO;
import com.scholar.mapper.DirectorMapper;
import com.scholar.model.Director;
import com.scholar.repository.DirectorRepository;
import com.scholar.repository.GroupRepository;
import com.scholar.request.DirectorRequest;
import com.scholar.service.generic.AbstractPersonService;

@Service
public class DirectorService extends AbstractPersonService<Director, DirectorDTO, DirectorRequest> {

	private DirectorRepository repository;
	private DirectorMapper mapper;
	
	@Autowired
	public DirectorService(DirectorRepository repository, DirectorMapper mapper,
			GroupRepository groupRepository, PasswordEncoder encoder) {	
		super(repository, mapper, groupRepository, encoder);
		
		this.repository = repository;
		this.mapper = mapper;
	}

}
