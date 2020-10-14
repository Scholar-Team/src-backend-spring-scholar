package com.scholar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scholar.mapper.BaseMapper;

public abstract class BaseService<M, D, R> {

	protected JpaRepository<M, Long> repository;
	protected BaseMapper<M, D, R> mapper;
	
	public BaseService(JpaRepository<M, Long> repository, BaseMapper<M, D, R> mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Transactional
	public Optional<D> save(R request) {
		return Optional.of(mapper.modelToDTO(
				repository.saveAndFlush(mapper.requestToModel(request))));
	}

	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
		repository.flush();
	}
	
	@Transactional
	public void putById(D dto) {
		repository.saveAndFlush(mapper.dtoToModel(dto));
	}
	
	public List<D> findAll() {
		return repository.findAll()
				.stream()
				.map(x -> mapper.modelToDTO(x))
				.collect(Collectors.toList());
	}

	public Optional<D> findById(Long id) {
		return Optional.of(mapper.modelToDTO(repository.findById(id).get()));
	}
}
