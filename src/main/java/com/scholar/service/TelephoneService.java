package com.scholar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.PersonDTO;
import com.scholar.dto.TelephoneDTO;
import com.scholar.mapper.PersonMapper;
import com.scholar.mapper.TelephoneMapper;
import com.scholar.model.Person;
import com.scholar.model.Telephone;
import com.scholar.repository.TelephoneRepository;
import com.scholar.request.TelephoneRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class TelephoneService 
	extends BaseService<Telephone, TelephoneDTO, TelephoneRequest> {

	private TelephoneRepository repository;
	private TelephoneMapper mapper;
	
	private PersonMapper personMapper;
	
	@Autowired
	public TelephoneService(
			TelephoneRepository repository,
			TelephoneMapper mapper,
			PersonMapper personMapper,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		
		this.personMapper = personMapper;
	}
	
	@Transactional(readOnly = true)
	public Optional<PersonDTO> findPersonById(Long id) {
		Optional<Person> person = repository.findPersonById(id);
		
		if(person.isPresent())
			return Optional.of(personMapper.modelToDTO(person.get()));
		
		return Optional.empty();
	}
	
}
