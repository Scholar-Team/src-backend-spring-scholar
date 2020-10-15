package com.scholar.dto;

import java.util.List;

import com.scholar.enumerator.SchoolPeriod;
import com.scholar.model.Classroom;

import lombok.Data;

@Data
public class PeriodDTO {

	private Long id;
	private SchoolPeriod period;
	private List<Classroom> classrooms;
}
