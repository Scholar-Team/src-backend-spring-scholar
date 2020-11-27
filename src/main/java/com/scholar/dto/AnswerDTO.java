package com.scholar.dto;

import java.time.LocalDateTime;
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
public class AnswerDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String description;
	private LocalDateTime dateTime;
	
	@JsonIgnoreProperties({
		"classes",
		"classroom",
		"answers"
	})
	@ToString.Exclude
	private ActivityDTO activity;
	
	@JsonIgnoreProperties({
		"classroom", 
		"address", 
		"telephones",
		"roles"
	})
	@ToString.Exclude
	private StudentDTO student;
	
	@JsonIgnoreProperties({
		"answer"
	})
	@ToString.Exclude
	private Set<FeedbackDTO> feedbacks;
	
	@ToString.Exclude
	private FileDTO attachment;
}
