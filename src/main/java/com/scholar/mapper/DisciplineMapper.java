package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.DisciplineDTO;
import com.scholar.model.Discipline;
import com.scholar.request.DisciplineRequest;

@Component
public class DisciplineMapper extends BaseMapper<Discipline, DisciplineDTO, DisciplineRequest> {

	public DisciplineMapper() {
		super(Discipline.class, DisciplineDTO.class, DisciplineRequest.class);
	}
}
