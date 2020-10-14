package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.PersonDTO;
import com.scholar.mapper.PersonMapper;
import com.scholar.model.Person;
import com.scholar.repository.PersonRepository;
import com.scholar.request.PersonRequest;

@Service
public class PersonService extends BaseService<Person, PersonDTO, PersonRequest> {

	private PersonRepository repository;
	
	@Autowired
	public PersonService(PersonRepository repository, PersonMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
}
