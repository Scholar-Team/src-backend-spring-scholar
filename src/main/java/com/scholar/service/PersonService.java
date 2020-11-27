package com.scholar.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.dto.PersonDTO;
import com.scholar.mapper.PersonMapper;
import com.scholar.model.Person;
import com.scholar.model.Role;
import com.scholar.repository.PersonRepository;
import com.scholar.repository.RoleRepository;
import com.scholar.request.PersonRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;

@Service
public class PersonService 
	extends BaseService<Person, PersonDTO, PersonRequest> {

	private PersonRepository repository;
	private PersonMapper mapper;
	private RoleRepository roleRepository;
	
	@Autowired
	public PersonService(
			PersonRepository repository,
			PersonMapper mapper,
			RoleRepository roleRepository,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		this.roleRepository = roleRepository;
	}
	
	@Override
	@Transactional
	public Optional<PersonDTO> save(PersonRequest request) {
		Person person = configPerson(mapper.requestToModel(request));
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(person)));
	}
	
	private Person configPerson(Person person) {
		Set<Role> roles = new HashSet<>();
		
		for(Role role : person.getRoles()) {
			roles.add(roleRepository.findById(role.getId()).get());
		}
		
		person.setRoles(roles);
		
		return person;
	}
	
	/*private void sendEmail(Person person) {
		Message message = Message.builder()
				.matter(person.getName() + " - Usuário cadastrado com sucesso!")
				.body("account-created.html")
				.variable("person", person)
				.recipient(person.getEmail())
				.build();
		
		emailService.send(message);
	}*/
}
