package com.scholar.controller;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

import com.scholar.service.IBaseService;

public class BaseController<M, D, R> {

	private IBaseService<M, D, R> service;
	
	public BaseController(IBaseService<M, D, R> service) {
		this.service = service;
	}
	
	public ResponseEntity<?> findAll() {
		try {
			return ResponseEntity.ok().body(service.findAll());
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<?> findById(Long id) {
		try {
			return ResponseEntity.ok().body(service.findById(id).get());
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
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
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	public ResponseEntity<?> putById(Long id, R request) {
		Optional<D> dto = service.findById(id);
		
		if(dto.isPresent()) {
			BeanUtils.copyProperties(request, dto.get(), "id");
			service.putById(dto.get());
			
			return ResponseEntity.ok(dto.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
