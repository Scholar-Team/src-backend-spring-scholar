package com.scholar.service.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scholar.dto.PersonDTO;
import com.scholar.mapper.BaseMapper;
import com.scholar.model.Group;
import com.scholar.model.Person;
import com.scholar.repository.GroupRepository;
import com.scholar.request.PersonRequest;

public abstract class AbstractPersonService<M extends Person, 
	D extends PersonDTO, R extends PersonRequest> 
	extends BaseService<M, D, R> {

	protected GroupRepository groupRepository;
	protected PasswordEncoder encoder;
	
	public AbstractPersonService(JpaRepository<M, Long> repository,
			BaseMapper<M, D, R> mapper, GroupRepository groupRepository,
			PasswordEncoder encoder) {
		super(repository, mapper);
		
		this.groupRepository = groupRepository;
		this.encoder = encoder;
	}
	
	@Override
	public Optional<D> save(R request) {
		M model = configModel(mapper.requestToModel(request));
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(model)));
	}
	
	private M configModel(M model) {
		List<Group> groups = new ArrayList<>();
		
		for(Group group : model.getGroups()) {
			groups.add(groupRepository.findById(group.getId()).get());
		}
		
		model.getTelephones()
			.stream()
			.forEach(x -> x.setPerson(model));
		
		model.setGroups(groups);
		model.setPassword(encoder.encode(model.getPassword()));
		
		return model;
	}

}
