package com.scholar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.StudentDTO;
import com.scholar.mapper.StudentMapper;
import com.scholar.model.Student;
import com.scholar.repository.StudentRepository;
import com.scholar.request.StudentRequest;

@Service
public class StudentService extends BaseService<Student, StudentDTO, StudentRequest> {

	private StudentRepository repository;
	private StudentMapper mapper;
	
	@Autowired
	public StudentService(StudentRepository repository, StudentMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	public Optional<StudentDTO> save(StudentRequest request) {
		Student student = mapper.requestToModel(request);
		
		student.getTelephones()
			.stream()
			.forEach(x -> x.setPerson(student));
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(student)));
	}

}
