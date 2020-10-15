package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.SchoolDTO;
import com.scholar.mapper.SchoolMapper;
import com.scholar.model.School;
import com.scholar.repository.SchoolRepository;
import com.scholar.request.SchoolRequest;

@Service
public class SchoolService extends BaseService<School, SchoolDTO, SchoolRequest> {

	private SchoolRepository repository;
	private SchoolMapper mapper;
	
	@Autowired
	public SchoolService(SchoolRepository repository, SchoolMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
	}

}
