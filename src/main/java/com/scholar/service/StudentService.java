package com.scholar.service;

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
	
	@Autowired
	public StudentService(StudentRepository repository, StudentMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
}
