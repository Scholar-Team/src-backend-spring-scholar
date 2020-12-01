package com.scholar.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	
	@ToString.Exclude
	private FileDTO file;
	
	@JsonIgnoreProperties({
		"disciplines"
	})
	@ToString.Exclude
	private Set<ClassroomDTO> classrooms;
	
	@JsonIgnoreProperties({
		"telephones",
		"address",
		"roles",
		"address",
		"telephones",
		"birthDate",
		"disciplines"
	})
	@ToString.Exclude
	private Set<TeacherDTO> teachers;
	
	@JsonIgnore
	private Set<ClassDTO> classes;
}
