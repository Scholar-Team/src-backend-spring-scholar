package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.FeedbackDTO;
import com.scholar.model.Feedback;
import com.scholar.request.FeedbackRequest;

@Component
public class FeedbackMapper extends BaseMapper<Feedback, FeedbackDTO, FeedbackRequest> {

	public FeedbackMapper() {
		super(Feedback.class, FeedbackDTO.class, FeedbackRequest.class);
	}
}