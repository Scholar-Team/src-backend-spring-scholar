package com.scholar.service.generic;

import java.util.List;
import java.util.Optional;

public interface IBaseService<M, D, R> {

	Optional<D> save(R request);
	void deleteById(Long id);
	void putById(D dto);
	List<D> findAll();
	Optional<D> findById(Long id);
}
