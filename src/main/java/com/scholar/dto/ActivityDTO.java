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
public class ActivityDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	private String description;
	private ZonedDateTime initialDate;
	private ZonedDateTime finalDate;
	
	@JsonIgnoreProperties({
		"size",
		"contentType",
		"fullName"
	})
	@ToString.Exclude
	private FileDTO attachment;
	
	@JsonIgnoreProperties({
		"students",
		"disciplines",
		"period"
	})
	@ToString.Exclude
	private ClassroomDTO classroom;
	
	@JsonIgnoreProperties({
		"activities",
		"discipline",
	})
	@ToString.Exclude
	private Set<ClassDTO> classes;
	
	@JsonIgnoreProperties({
		"activity",
		"feedbacks"
	})
	@ToString.Exclude
	private Set<AnswerDTO> answers;
}
