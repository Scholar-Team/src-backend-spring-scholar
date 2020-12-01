package com.scholar.dto;

import java.util.Set;

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
public class ClassroomDTO {

	private Long id;
	private String name;
	
	@JsonIgnoreProperties({
		"classrooms"
	})
	@ToString.Exclude
	private Set<DisciplineDTO> disciplines;
	
	@ToString.Exclude
	private FileDTO file;
	
	@JsonIgnoreProperties({
		"classroom", 
		"address", 
		"telephones",
		"roles"
	})
	@ToString.Exclude
	private Set<StudentDTO> students;
	
	@JsonIgnoreProperties({
		"classrooms"
	})
	@ToString.Exclude
	private PeriodDTO period;
}
