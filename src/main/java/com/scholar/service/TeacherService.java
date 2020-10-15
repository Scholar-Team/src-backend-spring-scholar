package com.scholar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.TeacherDTO;
import com.scholar.mapper.TeacherMapper;
import com.scholar.model.Teacher;
import com.scholar.repository.TeacherRepository;
import com.scholar.request.TeacherRequest;

@Service
public class TeacherService extends BaseService<Teacher, TeacherDTO, TeacherRequest> {

	private TeacherRepository repository;
	private TeacherMapper mapper;
	
	@Autowired
	public TeacherService(TeacherRepository repository, TeacherMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	public Optional<TeacherDTO> save(TeacherRequest request) {
		Teacher teacher = mapper.requestToModel(request);
		
		teacher.getTelephones()
			.stream()
			.forEach(x -> x.setPerson(teacher));
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(teacher)));
	}

}
