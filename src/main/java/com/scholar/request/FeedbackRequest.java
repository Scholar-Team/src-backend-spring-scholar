package com.scholar.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequest {

	private Long id;
	private String feedback;
	private String grade;
	private FileRequest attachment;
	private AnswerRequest answer;
	private TeacherRequest teacher;
}
