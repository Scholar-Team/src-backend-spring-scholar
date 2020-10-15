package com.scholar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
	private PersonMapper mapper;
	
	@Autowired
	public PersonService(PersonRepository repository, PersonMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public Optional<PersonDTO> save(PersonRequest request) {
		return null;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
	}

	@Override
	@Transactional
	public void putById(PersonDTO dto) {		
	}

	@Override
	public List<PersonDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(x -> mapper.modelToDTO(x))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<PersonDTO> findById(Long id) {
		return Optional.of(mapper.modelToDTO(repository.findById(id).get()));
	}
}
