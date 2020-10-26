package com.scholar.dto;

import java.util.List;

import com.scholar.model.Classroom;
import com.scholar.model.enumeration.SchoolPeriod;

import lombok.Data;

@Data
public class PeriodDTO {

	private Long id;
	private SchoolPeriod period;
	private List<Classroom> classrooms;
}
