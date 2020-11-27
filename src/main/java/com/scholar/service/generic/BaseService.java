package com.scholar.service.generic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.beanutils.MyBeanUtils;
import com.scholar.mapper.BaseMapper;
import com.scholar.security.permissions.data.AuthData;

public abstract class BaseService<M, D, R> implements IBaseService<M, D, R> {

	protected JpaRepository<M, Long> repository;
	protected BaseMapper<M, D, R> mapper;
	protected AuthData authData;
	
	public BaseService(
			JpaRepository<M, Long> repository, 
			BaseMapper<M, D, R> mapper,
			AuthData authData) {
		
		this.repository = repository;
		this.mapper = mapper;
		this.authData = authData;
	}
	
	@Override
	@Transactional
	public Optional<D> save(R request) {
		return Optional.of(mapper.modelToDTO(
				repository.saveAndFlush(mapper.requestToModel(request))));
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
		repository.flush();
	}
	
	@Override
	@Transactional
	public Optional<D> putById(Long id, R request) {
		M modelRequest = mapper.requestToModel(request);
		M model = repository.findById(id).get();
		
		BeanUtils.copyProperties(modelRequest, model, "id");
		
		return Optional.of(mapper.modelToDTO(repository.saveAndFlush(model)));
	}
	
	@Override
	@Transactional
	public Optional<D> patchById(Long id, R request) {
		M modelrequest = mapper.requestToModel(request);	
		M model = repository.findById(id).get();
		
		MyBeanUtils.copyNonNullProperties(modelrequest, model);
		
		return Optional.of(mapper.modelToDTO(repository.save(model)));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<D> findAll() {
		return repository.findAll()
				.stream()
				.map(x -> mapper.modelToDTO(x))
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<D> findById(Long id) {
		return Optional.of(mapper.modelToDTO(repository.findById(id).get()));
	}
	
	@Transactional(readOnly = true)
	public Boolean exists(Long id) {
		return repository.existsById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return repository.count();
	}
}
