package com.scholar.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.ClassroomDTO;
import com.scholar.dto.DirectorDTO;
import com.scholar.mapper.ClassroomMapper;
import com.scholar.mapper.DirectorMapper;
import com.scholar.mapper.FileMapper;
import com.scholar.mapper.PeriodMapper;
import com.scholar.model.Classroom;
import com.scholar.model.Director;
import com.scholar.model.File;
import com.scholar.repository.ClassroomRepository;
import com.scholar.repository.PeriodRepository;
import com.scholar.request.ClassroomRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class ClassroomService 
	extends BaseService<Classroom, ClassroomDTO, ClassroomRequest> {

	private ClassroomRepository repository;
	private ClassroomMapper mapper;
	
	private PeriodRepository periodRepository;
	private PeriodMapper periodMapper;
	private DirectorMapper directorMapper;
	private FileMapper fileMapper;
	private FileService fileService;
	
	@Autowired
	public ClassroomService(
			ClassroomRepository repository,
			ClassroomMapper mapper,
			PeriodRepository periodRepository,
			DirectorMapper directorMapper,
			PeriodMapper periodMapper,
			FileMapper fileMapper,
			FileService fileService,
			AuthData authData) {	
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.periodRepository = periodRepository;
		this.directorMapper = directorMapper;
		this.periodMapper = periodMapper;
		this.fileMapper = fileMapper;
		this.fileService = fileService;
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		Classroom classroom = repository.findById(id).get();
		
		classroom.getStudents().forEach(x -> {
			x.setClassroom(null);
		});
		
		classroom.getDisciplines().forEach(x -> {
			x.getClassrooms().remove(classroom);
		});
		
		repository.delete(classroom);
	}
	
	@Override
	@Transactional
	public Optional<ClassroomDTO> save(ClassroomRequest request) {
		Classroom classroom = 
				configClassroom(mapper.requestToModel(request), request);
		
		return Optional.of(mapper
				.modelToDTO(repository.saveAndFlush(classroom)));
	}
	
	private Classroom configClassroom(Classroom classroom, ClassroomRequest request) {
		if(!Objects.isNull(request.getFile().getFile()))
			classroom.setFile(configFile(request));
		
		classroom.setPeriod(periodRepository
				.findById(classroom.getPeriod().getId()).get());
		
		return classroom;
	}
	
	@Transactional(readOnly = true)
	public Optional<DirectorDTO> findDirectorById(Long id) {
		Optional<Director> director = repository.findDirectorById(id);
		
		if(director.isPresent())
			return Optional.of(directorMapper.modelToDTO(director.get()));
		
		return Optional.empty();
	}
	
	private File configFile(ClassroomRequest request) {
		File file = fileMapper.dtoToModel(fileService
						.save(request.getFile()).get());
		
		return file;
	}
	
}
