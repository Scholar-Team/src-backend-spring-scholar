package com.scholar.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.SchoolDTO;
import com.scholar.dto.StudentDTO;
import com.scholar.mapper.FileMapper;
import com.scholar.mapper.SchoolMapper;
import com.scholar.mapper.StudentMapper;
import com.scholar.model.School;
import com.scholar.model.Student;
import com.scholar.repository.ClassroomRepository;
import com.scholar.repository.RoleRepository;
import com.scholar.repository.StudentRepository;
import com.scholar.request.StudentRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.AbstractPersonService;

@Service
public class StudentService
	extends AbstractPersonService<Student, StudentDTO, StudentRequest> {

	private StudentRepository repository;
	private StudentMapper mapper;
	
	private ClassroomRepository classroomRepository;
	private SchoolMapper schoolMapper;
	
	@Autowired
	public StudentService(
			StudentRepository repository,
			StudentMapper mapper,
			RoleRepository roleRepository,
			PasswordEncoder encoder,
			ClassroomRepository classroomRepository,
			SchoolMapper schoolMapper,
			AuthData authData,
			FileMapper fileMapper,
			FileService fileService) {
		super(repository, mapper, roleRepository, 
				encoder, authData, fileMapper, fileService);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.classroomRepository = classroomRepository;
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
	public Optional<StudentDTO> save(StudentRequest request) {
		Student student = configStudent(mapper.requestToModel(request), request);
		
		return Optional.of(mapper
				.modelToDTO(repository.saveAndFlush(student)));
	}
	
	private Student configStudent(Student student, StudentRequest request) {
		student = configModel(student);
		
		student.addRole(roleRepository.findById(3L).get());
		
		student.setClassroom(classroomRepository
				.findById(student.getClassroom().getId()).get());
		
		if(!Objects.isNull(request.getFile().getFile()))
			student.setFile(configFile(request));
		
		return student;
	}

}
