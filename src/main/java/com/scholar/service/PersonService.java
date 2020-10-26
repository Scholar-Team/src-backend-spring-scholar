package com.scholar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.PersonDTO;
import com.scholar.mapper.PersonMapper;
import com.scholar.model.Group;
import com.scholar.model.Person;
import com.scholar.repository.GroupRepository;
import com.scholar.repository.PersonRepository;
import com.scholar.request.PersonRequest;
import com.scholar.service.generic.BaseService;

@Service
public class PersonService extends BaseService<Person, PersonDTO, PersonRequest> {

	private PersonRepository repository;
	private PersonMapper mapper;
	private GroupRepository groupRepository;
	
	@Autowired
	public PersonService(PersonRepository repository, PersonMapper mapper,
			GroupRepository groupRepository) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
		this.groupRepository = groupRepository;
	}
	
	@Override
	public Optional<PersonDTO> save(PersonRequest request) {
		Person person = configPerson(mapper.requestToModel(request));
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(person)));
	}
	
	private Person configPerson(Person person) {
		List<Group> groups = new ArrayList<>();
		
		for(Group group : person.getGroups()) {
			groups.add(groupRepository.findById(group.getId()).get());
		}
		
		person.setGroups(groups);
		
		return person;
	}
}
