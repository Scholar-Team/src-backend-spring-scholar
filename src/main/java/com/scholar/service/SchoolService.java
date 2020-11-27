package com.scholar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.ClassroomDTO;
import com.scholar.dto.DirectorDTO;
import com.scholar.dto.DisciplineDTO;
import com.scholar.dto.PeriodDTO;
import com.scholar.dto.SchoolDTO;
import com.scholar.dto.StudentDTO;
import com.scholar.dto.TeacherDTO;
import com.scholar.mapper.ClassroomMapper;
import com.scholar.mapper.DirectorMapper;
import com.scholar.mapper.DisciplineMapper;
import com.scholar.mapper.PeriodMapper;
import com.scholar.mapper.SchoolMapper;
import com.scholar.mapper.StudentMapper;
import com.scholar.mapper.TeacherMapper;
import com.scholar.model.Director;
import com.scholar.model.School;
import com.scholar.repository.SchoolRepository;
import com.scholar.request.SchoolRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class SchoolService extends BaseService<School, SchoolDTO, SchoolRequest> {

	private SchoolRepository repository;
	private SchoolMapper mapper;
	
	private DirectorMapper directorMapper;
	private PeriodMapper periodMapper;
	private StudentMapper studentMapper;
	private TeacherMapper teacherMapper;
	private DisciplineMapper disciplineMapper;
	private ClassroomMapper classroomMapper;
	
	@Autowired
	public SchoolService(
			SchoolRepository repository,
			SchoolMapper mapper,
			DirectorMapper directorMapper,
			PeriodMapper periodMapper,
			StudentMapper studentMapper,
			TeacherMapper teacherMapper,
			DisciplineMapper disciplineMapper,
			ClassroomMapper classroomMapper,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.directorMapper = directorMapper;
		this.periodMapper = periodMapper;
		this.studentMapper = studentMapper;
		this.teacherMapper = teacherMapper;
		this.disciplineMapper = disciplineMapper;
		this.classroomMapper = classroomMapper;
	}
	
	@Transactional(readOnly = true)
	public Optional<DirectorDTO> findDirectorById(Long id) {
		Optional<Director> director = repository.findDirectorById(id);
		
		if(director.isPresent())
			return Optional.of(directorMapper.modelToDTO(director.get()));
		
		return Optional.empty();
	}
	
	@Transactional(readOnly = true)
	public List<PeriodDTO> findPeriodsById(Long id) {
		return repository.findPeriodsById(id)
				.stream()
				.map(x -> periodMapper.modelToDTO(x))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<StudentDTO> findStudentsById(Long id) {
		return repository.findStudentsById(id)
				.stream()
				.map(x -> studentMapper.modelToDTO(x))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<ClassroomDTO> findClassroomsById(Long id) {
		return repository.findClassroomsById(id)
				.stream()
				.map(x -> classroomMapper.modelToDTO(x))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<TeacherDTO> findTeachersById(Long id) {
		return repository.findTeachersById(id)
				.stream()
				.map(x -> teacherMapper.modelToDTO(x))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<DisciplineDTO> findDisciplinesById(Long id) {
		return repository.findDisciplinesById(id)
				.stream()
				.map(x -> disciplineMapper.modelToDTO(x))
				.collect(Collectors.toList());
	}

}
