package com.scholar.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.scholar.model.Classroom;
import com.scholar.model.Teacher;

import lombok.Data;

@Data
public class DisciplineRequest {

	@NotBlank
	private String name;
	
	private List<Classroom> classrooms;
	private List<Teacher> teachers;
}
