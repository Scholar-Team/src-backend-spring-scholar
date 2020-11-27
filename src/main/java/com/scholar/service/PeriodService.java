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
import com.scholar.dto.StudentDTO;
import com.scholar.dto.TeacherDTO;
import com.scholar.mapper.ClassroomMapper;
import com.scholar.mapper.DirectorMapper;
import com.scholar.mapper.DisciplineMapper;
import com.scholar.mapper.PeriodMapper;
import com.scholar.mapper.StudentMapper;
import com.scholar.mapper.TeacherMapper;
import com.scholar.model.Director;
import com.scholar.model.Period;
import com.scholar.repository.DirectorRepository;
import com.scholar.repository.PeriodRepository;
import com.scholar.request.PeriodRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class PeriodService extends BaseService<Period, PeriodDTO, PeriodRequest> {

	private PeriodRepository repository;
	private PeriodMapper mapper;
	
	private DirectorRepository directorRepository;
	private DirectorMapper directorMapper;
	private ClassroomMapper classroomMapper;
	private StudentMapper studentMapper;
	private DisciplineMapper disciplineMapper;
	private TeacherMapper teacherMapper;
	
	@Autowired
	public PeriodService(
			PeriodRepository repository,
			PeriodMapper mapper, 
			DirectorMapper directorMapper,
			DirectorRepository directorRepository,
			ClassroomMapper classroomMapper,
			StudentMapper studentMapper,
			DisciplineMapper disciplineMapper,
			TeacherMapper teacherMapper,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.directorMapper = directorMapper;
		this.directorRepository = directorRepository;
		this.classroomMapper = classroomMapper;
		this.studentMapper = studentMapper;
		this.disciplineMapper = disciplineMapper;
		this.teacherMapper = teacherMapper;
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		Period period = repository.findById(id).get();
		
		period.getClassrooms().forEach(x -> {
			x.getStudents().forEach(z -> {
				z.setClassroom(null);
			});
		});
		
		repository.delete(period);
	}
	
	@Transactional(readOnly = true)
	public Optional<DirectorDTO> findDirectorById(Long id) {
		Optional<Director> director = repository.findDirectorById(id);
		
		if(director.isPresent())
			return Optional.of(directorMapper.modelToDTO(director.get()));
		
		return Optional.empty();
	}
	
	@Override
	@Transactional
	public Optional<PeriodDTO> save(PeriodRequest request) {
		Period period = configPeriod(mapper.requestToModel(request));
		
		return Optional.of(mapper.modelToDTO(repository.save(period)));
	}
	
	private Period configPeriod(Period period) {
		period.setSchool(directorRepository
				.findSchoolById(authData.getPersonId()).get());
		
		return period;
	}
	
	@Transactional(readOnly = true)
	public List<ClassroomDTO> findClassroomsById(Long id) {
		return repository.findClassroomsById(id)
				.stream()
				.map(x -> classroomMapper.modelToDTO(x))
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
	public List<DisciplineDTO> findDisciplinesById(Long id) {
		return repository.findDisciplinesById(id)
				.stream()
				.map(x -> disciplineMapper.modelToDTO(x))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<TeacherDTO> findTeachersById(Long id) {
		return repository.findTeachersById(id)
				.stream()
				.map(x -> teacherMapper.modelToDTO(x))
				.collect(Collectors.toList());
	}

}
