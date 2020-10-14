package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.ActivityDTO;
import com.scholar.mapper.ActivityMapper;
import com.scholar.model.Activity;
import com.scholar.repository.ActivityRepository;
import com.scholar.request.ActivityRequest;

@Service
public class ActivityService 
	extends BaseService<Activity, ActivityDTO, ActivityRequest> {

	private ActivityRepository repository;
	
	@Autowired
	public ActivityService(ActivityRepository repository, ActivityMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
	
}
