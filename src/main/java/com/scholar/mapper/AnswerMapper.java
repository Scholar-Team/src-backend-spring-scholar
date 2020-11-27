package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.AnswerDTO;
import com.scholar.model.Answer;
import com.scholar.request.AnswerRequest;

@Component
public class AnswerMapper extends BaseMapper<Answer, AnswerDTO, AnswerRequest> {

	public AnswerMapper() {
		super(Answer.class, AnswerDTO.class, AnswerRequest.class);
	}
}
