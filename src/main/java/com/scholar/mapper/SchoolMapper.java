package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.SchoolDTO;
import com.scholar.model.School;
import com.scholar.request.SchoolRequest;

@Component
public class SchoolMapper extends BaseMapper<School, SchoolDTO, SchoolRequest> {

	public SchoolMapper() {
		super(School.class, SchoolDTO.class, SchoolRequest.class);
	}
}
