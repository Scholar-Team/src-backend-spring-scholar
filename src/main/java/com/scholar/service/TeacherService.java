package com.scholar.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.ClassDTO;
import com.scholar.dto.SchoolDTO;
import com.scholar.dto.TeacherDTO;
import com.scholar.mapper.ClassMapper;
import com.scholar.mapper.FileMapper;
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
	private ClassMapper classMapper;
	
	@Autowired
	public TeacherService(
			TeacherRepository repository,
			TeacherMapper mapper,
			RoleRepository roleRepository,
			PasswordEncoder encoder,
			SchoolMapper schoolMapper,
			AuthData authData,
			FileMapper fileMapper,
			ClassMapper classMapper,
			FileService fileService) {
		super(repository, mapper, roleRepository, 
				encoder, authData, fileMapper, fileService);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.schoolMapper = schoolMapper;
		this.classMapper = classMapper;
	}
	
	@Transactional(readOnly = true)
	public Optional<SchoolDTO> findSchoolById(Long id) {
		Optional<School> school = repository.findSchoolById(id);
		
		if(school.isPresent())
			return Optional.of(schoolMapper.modelToDTO(school.get()));
		
		return Optional.empty();
	}
	
	@Transactional(readOnly = true)
	public List<ClassDTO> findClassesById(Long id) {
		return repository.findClassesById(id)
				.stream()
				.map(x -> classMapper.modelToDTO(x))
				.collect(Collectors.toList());
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
		Teacher teacher = configTeacher(mapper.requestToModel(request), request);
		
		return Optional.of(mapper
				.modelToDTO(repository.saveAndFlush(teacher)));
	}
	
	private Teacher configTeacher(Teacher teacher, TeacherRequest request) {
		teacher = configModel(teacher);
		
		teacher.addRole(roleRepository
				.findById(5L).get());
		
		if(!Objects.isNull(request.getFile().getFile()))
			teacher.setFile(configFile(request));
		
		return teacher;
	}

}
