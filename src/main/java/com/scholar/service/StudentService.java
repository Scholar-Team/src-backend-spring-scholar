package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scholar.dto.StudentDTO;
import com.scholar.mapper.StudentMapper;
import com.scholar.model.Student;
import com.scholar.repository.GroupRepository;
import com.scholar.repository.StudentRepository;
import com.scholar.request.StudentRequest;
import com.scholar.service.generic.AbstractPersonService;

@Service
public class StudentService extends AbstractPersonService<Student, StudentDTO, StudentRequest> {

	private StudentRepository repository;
	private StudentMapper mapper;
	
	@Autowired
	public StudentService(StudentRepository repository, StudentMapper mapper,
			GroupRepository groupRepository, PasswordEncoder encoder) {
		super(repository, mapper, groupRepository, encoder);
		
		this.repository = repository;
		this.mapper = mapper;
	}

}
