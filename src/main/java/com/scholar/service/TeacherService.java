package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scholar.dto.TeacherDTO;
import com.scholar.mapper.TeacherMapper;
import com.scholar.model.Teacher;
import com.scholar.repository.GroupRepository;
import com.scholar.repository.TeacherRepository;
import com.scholar.request.TeacherRequest;
import com.scholar.service.generic.AbstractPersonService;

@Service
public class TeacherService extends AbstractPersonService<Teacher, TeacherDTO, TeacherRequest> {

	private TeacherRepository repository;
	private TeacherMapper mapper;
	
	@Autowired
	public TeacherService(TeacherRepository repository, TeacherMapper mapper,
			GroupRepository groupRepository, PasswordEncoder encoder) {
		super(repository, mapper, groupRepository, encoder);
		
		this.repository = repository;
		this.mapper = mapper;
	}

}
