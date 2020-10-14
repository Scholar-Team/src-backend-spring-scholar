package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.ActivityDTO;
import com.scholar.model.Activity;
import com.scholar.request.ActivityRequest;

@Component
public class ActivityMapper extends BaseMapper<Activity, ActivityDTO, ActivityRequest> {

	public ActivityMapper() {
		super(Activity.class, ActivityDTO.class, ActivityRequest.class);
	}
}
