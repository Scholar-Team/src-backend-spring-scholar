package com.scholar.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.PeriodDTO;
import com.scholar.mapper.PeriodMapper;
import com.scholar.model.Period;
import com.scholar.repository.PeriodRepository;
import com.scholar.repository.SchoolRepository;
import com.scholar.request.PeriodRequest;

@Service
public class PeriodService extends BaseService<Period, PeriodDTO, PeriodRequest> {

	private PeriodRepository repository;
	private SchoolRepository schoolRepository;
	private PeriodMapper mapper;
	
	@Autowired
	public PeriodService(PeriodRepository repository, PeriodMapper mapper, 
			SchoolRepository schoolRepository) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
		this.schoolRepository = schoolRepository;
	}
	
	@Override
	@Transactional
	public Optional<PeriodDTO> save(PeriodRequest request) {
		Period period = mapper.requestToModel(request);
		period.setSchool(schoolRepository.findById(period.getSchool().getId()).get());
		
		return Optional.of(mapper.modelToDTO(repository.save(period)));
	}

}
