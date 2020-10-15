package com.scholar.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class DisciplineDTO {

	private Long id;
	private String name;
	
	@JsonIgnoreProperties({"disciplines"})
	private List<ClassroomDTO> classrooms;
	
	@JsonIgnoreProperties({"telephones", "address"})
	private List<TeacherDTO> teachers;
}
