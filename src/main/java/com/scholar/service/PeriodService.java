package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.PeriodDTO;
import com.scholar.mapper.PeriodMapper;
import com.scholar.model.Period;
import com.scholar.repository.PeriodRepository;
import com.scholar.request.PeriodRequest;

@Service
public class PeriodService extends BaseService<Period, PeriodDTO, PeriodRequest> {

	private PeriodRepository repository;
	
	@Autowired
	public PeriodService(PeriodRepository repository, PeriodMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
}
