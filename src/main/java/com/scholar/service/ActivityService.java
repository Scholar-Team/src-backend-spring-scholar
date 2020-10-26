package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.ActivityDTO;
import com.scholar.mapper.ActivityMapper;
import com.scholar.model.Activity;
import com.scholar.repository.ActivityRepository;
import com.scholar.request.ActivityRequest;
import com.scholar.service.generic.BaseService;

@Service
public class ActivityService extends BaseService<Activity, ActivityDTO, ActivityRequest> {

	private ActivityRepository repository;
	private ActivityMapper mapper;
	
	@Autowired
	public ActivityService(ActivityRepository repository, ActivityMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
	}
	
}
