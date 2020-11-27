package com.scholar.service;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.FeedbackDTO;
import com.scholar.mapper.FeedbackMapper;
import com.scholar.mapper.FileMapper;
import com.scholar.model.Feedback;
import com.scholar.model.File;
import com.scholar.repository.AnswerRepository;
import com.scholar.repository.FeedbackRepository;
import com.scholar.repository.TeacherRepository;
import com.scholar.request.FeedbackRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class FeedbackService extends BaseService<Feedback, FeedbackDTO, FeedbackRequest> {

	private FeedbackRepository repository;
	private FeedbackMapper mapper;
	
	private FileService fileService;
	private FileMapper fileMapper;
	private TeacherRepository teacherRepository;
	private AnswerRepository answerRepository;
	
	@Autowired
	public FeedbackService(
			FeedbackRepository repository,
			FeedbackMapper mapper,
			FileService fileService,
			FileMapper fileMapper,
			TeacherRepository teacherRepository,
			AnswerRepository answerRepository,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.fileService = fileService;
		this.fileMapper = fileMapper;
		this.teacherRepository = teacherRepository;
		this.answerRepository = answerRepository;
	}
	
	@Override
	@Transactional
	public Optional<FeedbackDTO> save(FeedbackRequest request) {
		Feedback feedback = configFeedback(mapper.requestToModel(request), request);
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(feedback)));
	}
	
	private Feedback configFeedback(Feedback feedback, FeedbackRequest request) {		
		if(!Objects.isNull(request.getAttachment().getFile()))
			feedback.setAttachment(configFile(request));
		
		feedback.setDateTime(ZonedDateTime.now());
		
		feedback.setAnswer(answerRepository
				.findById(feedback.getAnswer().getId()).get());
				
		feedback.setTeacher(teacherRepository
				.findById(feedback.getTeacher().getId()).get());
		
		return feedback;
	}
	
	private File configFile(FeedbackRequest request) {
		File file = fileMapper.dtoToModel(fileService
						.save(request.getAttachment()).get());
		
		return file;
	}
	
}
