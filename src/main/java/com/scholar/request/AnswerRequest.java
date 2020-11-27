package com.scholar.request;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {

	private Long id;
	private ActivityRequest activity;
	private StudentRequest student;
	private String description;
	private ZonedDateTime dateTime;
	private FileRequest attachment;
}
