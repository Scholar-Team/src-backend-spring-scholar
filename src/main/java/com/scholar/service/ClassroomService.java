package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.ClassroomDTO;
import com.scholar.mapper.ClassroomMapper;
import com.scholar.model.Classroom;
import com.scholar.repository.ClassroomRepository;
import com.scholar.request.ClassroomRequest;

@Service
public class ClassroomService extends BaseService<Classroom, ClassroomDTO, ClassroomRequest> {

	private ClassroomRepository repository;
	private ClassroomMapper mapper;
	
	@Autowired
	public ClassroomService(ClassroomRepository repository, ClassroomMapper mapper) {	
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
	}
	
}
