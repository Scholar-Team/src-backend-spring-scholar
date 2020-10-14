package com.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scholar.dto.PeriodDTO;
import com.scholar.model.Period;
import com.scholar.openapi.controller.PeriodControllerOpenApi;
import com.scholar.request.PeriodRequest;
import com.scholar.service.PeriodService;

@RestController
@CrossOrigin
@RequestMapping("/period")
public class PeriodController implements PeriodControllerOpenApi {

	private PeriodService service;
	private BaseController<Period, 
	PeriodDTO, PeriodRequest> base;
	
	@Autowired
	public PeriodController(PeriodService service) {		
		this.service = service;
		this.base = new BaseController<>(service);
	}

	@Override
	@GetMapping
	public ResponseEntity<?> findAll() {
		return base.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PeriodRequest request) {
		return base.save(request);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(@PathVariable Long id,
			@RequestBody PeriodRequest request) {
		return base.putById(id, request);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}
	
}
