package com.scholar.request;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineRequest {

	private Long id;
	
	@NotBlank
	private String name;
	
	@Builder.Default
	private Set<ClassroomRequest> classrooms = new HashSet<>();
	
	@Builder.Default
	private Set<TeacherRequest> teachers = new HashSet<>();
}
