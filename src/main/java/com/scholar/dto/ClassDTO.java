package com.scholar.dto;

import java.time.ZonedDateTime;
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
public class ClassDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	private String description;
	private String link;
	private ZonedDateTime date;
	
	@JsonIgnoreProperties({
		"size",
		"contentType",
		"fullName"
	})
	@ToString.Exclude
	private FileDTO attachment;
	
	@JsonIgnoreProperties({
		"classrooms",
		"answers"
	})
	@ToString.Exclude
	private DisciplineDTO discipline;
	
	@JsonIgnoreProperties({
		"classes",
		"classroom",
	})
	@ToString.Exclude
	private Set<ActivityDTO> activities;
}
