package com.scholar.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.DirectorDTO;
import com.scholar.dto.DisciplineDTO;
import com.scholar.dto.StudentDTO;
import com.scholar.dto.TeacherDTO;
import com.scholar.mapper.DirectorMapper;
import com.scholar.mapper.DisciplineMapper;
import com.scholar.mapper.FileMapper;
import com.scholar.mapper.StudentMapper;
import com.scholar.mapper.TeacherMapper;
import com.scholar.model.Director;
import com.scholar.model.Discipline;
import com.scholar.model.File;
import com.scholar.repository.ClassroomRepository;
import com.scholar.repository.DisciplineRepository;
import com.scholar.repository.TeacherRepository;
import com.scholar.request.DisciplineRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class DisciplineService 
	extends BaseService<Discipline, DisciplineDTO, DisciplineRequest> {

	private DisciplineRepository repository;
	private DisciplineMapper mapper;
	
	private ClassroomRepository classroomRepository;
	private TeacherRepository teacherRepository;
	private TeacherMapper teacherMapper;
	private StudentMapper studentMapper;
	private DirectorMapper directorMapper;
	private FileMapper fileMapper;
	private FileService fileService;
	
	@Autowired
	public DisciplineService(
			DisciplineRepository repository, 
			DisciplineMapper mapper,
			ClassroomRepository classroomRepository,
			TeacherRepository teacherRepository,
			TeacherMapper teacherMapper,
			StudentMapper studentMapper,
			DirectorMapper directorMapper,
			FileMapper fileMapper,
			FileService fileService,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.classroomRepository = classroomRepository;
		this.teacherRepository = teacherRepository;
		this.teacherMapper = teacherMapper;
		this.studentMapper = studentMapper;
		this.directorMapper = directorMapper;
		this.fileMapper = fileMapper;
		this.fileService = fileService;
	}
	
	@Transactional(readOnly = true)
	public Optional<DirectorDTO> findDirectorById(Long id) {
		Optional<Director> director = repository.findDirectorById(id);
		
		if(director.isPresent())
			return Optional.of(directorMapper.modelToDTO(director.get()));
		
		return Optional.empty();
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
	
	@Override
	@Transactional
	public Optional<DisciplineDTO> save(DisciplineRequest request) {
		Discipline discipline = configDiscipline(mapper.requestToModel(request), request);

		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(discipline)));
	}
	
	private Discipline configDiscipline(Discipline discipline, DisciplineRequest request) {
		if(!Objects.isNull(request.getFile().getFile()))
			discipline.setFile(configFile(request));
		
		discipline.setClassrooms(new HashSet<>());
		request.getClassrooms().forEach(x -> {
			discipline.addClassroom(classroomRepository
					.findById(x.getId()).get());
		});
		
		discipline.setTeachers(new HashSet<>());
		request.getTeachers().forEach(x -> {
			discipline.addTeacher(teacherRepository
					.findById(x.getId()).get());
		});
				
		return discipline;
	}
	
	private File configFile(DisciplineRequest request) {
		File file = fileMapper.dtoToModel(fileService
						.save(request.getFile()).get());
		
		return file;
	}

}
