package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.DirectorDTO;
import com.scholar.model.Director;
import com.scholar.request.DirectorRequest;

@Component
public class DirectorMapper extends BaseMapper<Director, DirectorDTO, DirectorRequest> {

	public DirectorMapper() {
		super(Director.class, DirectorDTO.class, DirectorRequest.class);
	}
}
