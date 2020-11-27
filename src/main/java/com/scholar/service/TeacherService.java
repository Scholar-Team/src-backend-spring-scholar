package com.scholar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.SchoolDTO;
import com.scholar.dto.TeacherDTO;
import com.scholar.mapper.SchoolMapper;
import com.scholar.mapper.TeacherMapper;
import com.scholar.model.School;
import com.scholar.model.Teacher;
import com.scholar.repository.RoleRepository;
import com.scholar.repository.TeacherRepository;
import com.scholar.request.TeacherRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.AbstractPersonService;

@Service
public class TeacherService
	extends AbstractPersonService<Teacher, TeacherDTO, TeacherRequest> {

	private TeacherRepository repository;
	private TeacherMapper mapper;
	
	private SchoolMapper schoolMapper;
	
	@Autowired
	public TeacherService(
			TeacherRepository repository,
			TeacherMapper mapper,
			RoleRepository roleRepository,
			PasswordEncoder encoder,
			SchoolMapper schoolMapper,
			AuthData authData) {
		super(repository, mapper, roleRepository, 
				encoder, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.schoolMapper = schoolMapper;
	}
	
	@Transactional(readOnly = true)
	public Optional<SchoolDTO> findSchoolById(Long id) {
		Optional<School> school = repository.findSchoolById(id);
		
		if(school.isPresent())
			return Optional.of(schoolMapper.modelToDTO(school.get()));
		
		return Optional.empty();
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		Teacher teacher = repository.findById(id).get();
		
		teacher.getDisciplines().forEach(x -> {
			x.getTeachers().remove(teacher);
		});
		
		repository.delete(teacher);
	}
	
	@Override
	@Transactional
	public Optional<TeacherDTO> save(TeacherRequest request) {
		Teacher teacher = configTeacher(mapper.requestToModel(request));
		
		return Optional.of(mapper
				.modelToDTO(repository.saveAndFlush(teacher)));
	}
	
	private Teacher configTeacher(Teacher teacher) {
		teacher = configModel(teacher);
		
		teacher.addRole(roleRepository
				.findById(5L).get());
		
		return teacher;
	}

}
