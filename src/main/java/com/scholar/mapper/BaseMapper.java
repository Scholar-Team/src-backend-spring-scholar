package com.scholar.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseMapper<M, D, R> {

	@Autowired
	private ModelMapper mapper;
	
	private Class<M> model;
	private Class<D> dto;
	
	public BaseMapper(Class<M> model, Class<D> dto, Class<R> request) {
		this.model = model;
		this.dto = dto;
	}
	
	public D modelToDTO(M model) {
		return mapper.map(model, dto);
	}
	
	public D requestToDTO(R request) {
		return mapper.map(request, dto);
	}
	
	public M dtoToModel(D dto) {
		return mapper.map(dto, model);
	}

	public M requestToModel(R request) {
		return mapper.map(request, model);
	}
}
