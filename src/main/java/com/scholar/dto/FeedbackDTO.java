package com.scholar.dto;

import java.time.ZonedDateTime;

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
public class FeedbackDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private ZonedDateTime dateTime;
	private String feedback;
	private String grade;
	
	@JsonIgnoreProperties({
		"size", 
		"contentType",
		"fullName"
	})
	@ToString.Exclude
	private FileDTO attachment;
	
	@JsonIgnoreProperties({
		"feedbacks"
	})
	@ToString.Exclude
	private AnswerDTO answer;
	
	@JsonIgnoreProperties({
		"address", 
		"telephones",
		"roles",
		"disciplines"
	})
	@ToString.Exclude
	private TeacherDTO teacher;
	
}
