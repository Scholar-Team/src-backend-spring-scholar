package com.scholar.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class ClassroomDTO {

	private Long id;
	private String name;
	
	@JsonIgnoreProperties({"classrooms"})
	private List<DisciplineDTO> disciplines;
	
	@JsonIgnoreProperties({"classroom", "address", "telephones"})
	private List<StudentDTO> students;
	
	@JsonIgnoreProperties({"classrooms"})
	private PeriodDTO period;
}
