package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.ClassDTO;
import com.scholar.mapper.ClassMapper;
import com.scholar.model.Class;
import com.scholar.repository.ClassRepository;
import com.scholar.request.ClassRequest;

@Service
public class ClassService extends BaseService<Class, ClassDTO, ClassRequest> {

	private ClassRepository repository;
	private ClassMapper mapper;
	
	@Autowired
	public ClassService(ClassRepository repository, ClassMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
	}
	
}
