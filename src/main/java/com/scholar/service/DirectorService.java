package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.DirectorDTO;
import com.scholar.mapper.DirectorMapper;
import com.scholar.model.Director;
import com.scholar.repository.DirectorRepository;
import com.scholar.request.DirectorRequest;

@Service
public class DirectorService extends BaseService<Director, DirectorDTO, DirectorRequest> {

	private DirectorRepository repository;
	
	@Autowired
	public DirectorService(DirectorRepository repository, DirectorMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
}
