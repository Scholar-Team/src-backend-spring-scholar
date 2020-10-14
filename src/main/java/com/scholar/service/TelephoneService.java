package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.TelephoneDTO;
import com.scholar.mapper.TelephoneMapper;
import com.scholar.model.Telephone;
import com.scholar.repository.TelephoneRepository;
import com.scholar.request.TelephoneRequest;

@Service
public class TelephoneService extends BaseService<Telephone, TelephoneDTO, TelephoneRequest> {

	private TelephoneRepository repository;
	
	@Autowired
	public TelephoneService(TelephoneRepository repository, TelephoneMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
}
