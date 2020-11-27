package com.scholar.controller.generic;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.scholar.exception.BusinessException;
import com.scholar.exception.NotResultException;
import com.scholar.exception.PersonNotFoundException;
import com.scholar.service.generic.IBaseService;

public class BaseController<M, D, R> {

	private IBaseService<M, D, R> service;	
	
	public BaseController(IBaseService<M, D, R> service) {
		this.service = service;
	}
	
	public ResponseEntity<?> findAll() {
		try {
			return ResponseEntity.ok().body(service.findAll());
			
		} catch(NotResultException ex) {
			throw new NotResultException();
			
		} catch (BusinessException e) {
			throw new BusinessException();
		}
	}
	
	public ResponseEntity<?> findById(Long id) {
		try {
			return ResponseEntity.ok()
					.body(service.findById(id).get());
			
		} catch(PersonNotFoundException ex) {
			throw new PersonNotFoundException(id);
			
		} catch (BusinessException e) {
			throw new BusinessException();
		}
	}
	
	public ResponseEntity<?> save(R request) {
		try {
			D dto = service.save(request).get();
			return ResponseEntity.accepted().body(dto);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<?> deleteById(Long id) {
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
			
		} catch (BusinessException e) {
			throw new BusinessException();
		}
	}
	
	public ResponseEntity<?> putById(Long id, R request) {
		try {
			return ResponseEntity.accepted().body(service.putById(id, request));
			
		} catch (PersonNotFoundException e) {
			throw new PersonNotFoundException(id);
			
		} catch (BusinessException e) {
			throw new BusinessException();
		}
	}
	
	public ResponseEntity<?> patchById(Long id, R request) {
		try {
			return ResponseEntity.accepted().body(service.patchById(id, request));
			
		} catch (PersonNotFoundException e) {
			throw new PersonNotFoundException(id);
			
		} catch (BusinessException e) {
			throw new BusinessException();
		}
	}
	
	public ResponseEntity<?> findListById(Collection<?> list) {
		try {			
			if(!list.isEmpty())
				return ResponseEntity.ok().body(list);
			else
				throw new NotResultException();
			
		} catch (NotResultException e) {
			throw new NotResultException();
		}
	}
}
