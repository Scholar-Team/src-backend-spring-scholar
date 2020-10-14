package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.DisciplineDTO;
import com.scholar.mapper.DisciplineMapper;
import com.scholar.model.Discipline;
import com.scholar.repository.DisciplineRepository;
import com.scholar.request.DisciplineRequest;

@Service
public class DisciplineService extends BaseService<Discipline, DisciplineDTO, DisciplineRequest> {

	private DisciplineRepository repository;
	
	@Autowired
	public DisciplineService(DisciplineRepository repository, DisciplineMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
}
