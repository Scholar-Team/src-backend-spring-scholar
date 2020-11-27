package com.scholar.service;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.ClassDTO;
import com.scholar.dto.DisciplineDTO;
import com.scholar.dto.StudentDTO;
import com.scholar.dto.TeacherDTO;
import com.scholar.mapper.ClassMapper;
import com.scholar.mapper.DisciplineMapper;
import com.scholar.mapper.FileMapper;
import com.scholar.mapper.StudentMapper;
import com.scholar.mapper.TeacherMapper;
import com.scholar.model.Class;
import com.scholar.model.File;
import com.scholar.repository.ClassRepository;
import com.scholar.repository.DisciplineRepository;
import com.scholar.request.ClassRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class ClassService 
	extends BaseService<Class, ClassDTO, ClassRequest> {

	private ClassRepository repository;
	private ClassMapper mapper;
	
	private DisciplineRepository disciplineRepository;
	private DisciplineMapper disciplineMapper;
	private TeacherMapper teacherMapper;
	private StudentMapper studentMapper;
	private FileService fileService;
	private FileMapper fileMapper;
	
	@Autowired
	public ClassService(
			ClassRepository repository,
			ClassMapper mapper,
			DisciplineRepository disciplineRepository,
			DisciplineMapper disciplineMapper,
			TeacherMapper teacherMapper,
			StudentMapper studentMapper,
			FileService fileService,
			FileMapper fileMapper,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.disciplineRepository = disciplineRepository;
		this.disciplineMapper = disciplineMapper;
		this.teacherMapper = teacherMapper;
		this.studentMapper = studentMapper;
		this.fileService = fileService;
		this.fileMapper = fileMapper;
	}
	
	@Transactional(readOnly = true)
	public Set<TeacherDTO> findTeachersById(Long id) {
		return repository.findTeachersById(id)
				.stream()
				.map(x -> teacherMapper.modelToDTO(x))
				.collect(Collectors.toSet());
	}
	
	@Transactional(readOnly = true)
	public Set<StudentDTO> findStudentsById(Long id) {
		return repository.findStudentsById(id)
				.stream()
				.map(x -> studentMapper.modelToDTO(x))
				.collect(Collectors.toSet());
	}
	
	@Transactional(readOnly = true)
	public Optional<DisciplineDTO> findDisciplineById(Long id) {
		return Optional.of(disciplineMapper
				.modelToDTO(repository.findDisciplineById(id).get()));
	}
	
	@Override
	@Transactional
	public Optional<ClassDTO> save(ClassRequest request) {
		Class classModel = configClass(mapper.requestToModel(request), request);
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(classModel)));
	}
	
	private Class configClass(Class classModel, ClassRequest request) {
		if(!Objects.isNull(request.getAttachment().getFile()))
			classModel.setAttachment(configFile(request));
		
		classModel.setDiscipline(disciplineRepository
				.findById(classModel.getDiscipline().getId()).get());
		
		classModel.setDate(ZonedDateTime.now());
		
		return classModel;
	}
	
	private File configFile(ClassRequest request) {
		File file = fileMapper.dtoToModel(fileService
						.save(request.getAttachment()).get());
		
		return file;
	}
	
}
