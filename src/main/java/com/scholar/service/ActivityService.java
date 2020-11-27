package com.scholar.service;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.ActivityDTO;
import com.scholar.mapper.ActivityMapper;
import com.scholar.mapper.FileMapper;
import com.scholar.model.Activity;
import com.scholar.model.File;
import com.scholar.model.Class;
import com.scholar.repository.ActivityRepository;
import com.scholar.repository.ClassRepository;
import com.scholar.repository.ClassroomRepository;
import com.scholar.request.ActivityRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class ActivityService
	extends BaseService<Activity, ActivityDTO, ActivityRequest> {

	private ActivityRepository repository;
	private ActivityMapper mapper;
	
	private FileService fileService;
	private FileMapper fileMapper;
	private ClassroomRepository classroomRepository;
	private ClassRepository classRepository;
	
	@Autowired
	public ActivityService(
			ActivityRepository repository,
			ActivityMapper mapper,
			FileService fileService,
			FileMapper fileMapper,
			ClassroomRepository classroomRepository,
			ClassRepository classRepository,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.fileService = fileService;
		this.fileMapper = fileMapper;
		this.classroomRepository = classroomRepository;
		this.classRepository = classRepository;
	}
	
	@Override
	@Transactional
	public Optional<ActivityDTO> save(ActivityRequest request) {
		Activity activity = configActivity(mapper.requestToModel(request), request);
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(activity)));
	}
	
	private Activity configActivity(Activity activity, ActivityRequest request) {
		if(!Objects.isNull(request.getAttachment().getFile()))
			activity.setAttachment(configFile(request));
		
		activity.setClasses(new HashSet<Class>());
		request.getClasses().forEach(x -> {
			activity.addClass(classRepository
					.findById(x.getId()).get());
		});
		
		activity.setClassroom(classroomRepository
				.findById(activity.getClassroom().getId()).get());
		
		activity.setInitialDate(ZonedDateTime.now());
		
		return activity;
	}
	
	private File configFile(ActivityRequest request) {
		File file = fileMapper.dtoToModel(fileService
						.save(request.getAttachment()).get());
		
		return file;
	}
	
}
