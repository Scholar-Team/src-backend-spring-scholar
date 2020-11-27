package com.scholar.service.generic;

import java.util.List;
import java.util.Optional;

public interface IBaseService<M, D, R> {

	Optional<D> save(R request);
	void deleteById(Long id);
	Optional<D> putById(Long id, R request);
	Optional<D> patchById(Long id, R request);
	List<D> findAll();
	Optional<D> findById(Long id);
	Long count();
}
