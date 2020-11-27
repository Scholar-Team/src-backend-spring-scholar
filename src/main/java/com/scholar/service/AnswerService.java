package com.scholar.service;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.AnswerDTO;
import com.scholar.mapper.AnswerMapper;
import com.scholar.mapper.FileMapper;
import com.scholar.model.Answer;
import com.scholar.model.File;
import com.scholar.repository.ActivityRepository;
import com.scholar.repository.AnswerRepository;
import com.scholar.repository.StudentRepository;
import com.scholar.request.AnswerRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class AnswerService
	extends BaseService<Answer, AnswerDTO, AnswerRequest> {

	private AnswerRepository repository;
	private AnswerMapper mapper;
	
	private FileService fileService;
	private FileMapper fileMapper;
	private ActivityRepository activityRepository;
	private StudentRepository studentRepository;
	
	@Autowired
	public AnswerService(
			AnswerRepository repository,
			AnswerMapper mapper,
			FileService fileService,
			FileMapper fileMapper,
			ActivityRepository activityRepository,
			StudentRepository studentRepository,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.fileMapper = fileMapper;
		this.fileService = fileService;
		this.activityRepository = activityRepository;
		this.studentRepository = studentRepository;
	}
	
	@Override
	@Transactional
	public Optional<AnswerDTO> save(AnswerRequest request) {
		Answer answer = configAnswer(mapper.requestToModel(request), request);
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(answer)));
	}
	
	private Answer configAnswer(Answer answer, AnswerRequest request) {
		if(!Objects.isNull(request.getAttachment().getFile()))
			answer.setAttachment(configFile(request));
		
		answer.setDateTime(ZonedDateTime.now());
		
		answer.setStudent(studentRepository
				.findById(answer.getStudent().getId()).get());
		
		answer.setActivity(activityRepository
				.findById(answer.getActivity().getId()).get());
		
		return answer;
	}
	
	private File configFile(AnswerRequest request) {
		File file = fileMapper.dtoToModel(fileService
						.save(request.getAttachment()).get());
		
		return file;
	}
	
}
