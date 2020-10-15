package com.scholar.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.DirectorDTO;
import com.scholar.mapper.DirectorMapper;
import com.scholar.model.Director;
import com.scholar.repository.DirectorRepository;
import com.scholar.request.DirectorRequest;

@Service
public class DirectorService extends BaseService<Director, DirectorDTO, DirectorRequest> {

	private DirectorRepository repository;
	private DirectorMapper mapper;
	
	@Autowired
	public DirectorService(DirectorRepository repository, DirectorMapper mapper) {	
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
	@Transactional
	public Optional<DirectorDTO> save(DirectorRequest request) {
		Director director = mapper.requestToModel(request);
		
		director.getTelephones()
			.stream()
			.forEach(x -> x.setPerson(director));
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(director)));
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
		repository.flush();
	}

}
