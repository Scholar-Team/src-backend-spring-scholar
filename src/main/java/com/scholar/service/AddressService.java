package com.scholar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.AddressDTO;
import com.scholar.dto.PersonDTO;
import com.scholar.mapper.AddressMapper;
import com.scholar.mapper.PersonMapper;
import com.scholar.model.Address;
import com.scholar.model.Person;
import com.scholar.repository.AddressRepository;
import com.scholar.request.AddressRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class AddressService extends BaseService<Address, AddressDTO, AddressRequest> {

	private AddressRepository repository;
	private AddressMapper mapper;
	
	private PersonMapper personMapper;
	
	@Autowired
	public AddressService(
			AddressRepository repository,
			AddressMapper mapper,
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
