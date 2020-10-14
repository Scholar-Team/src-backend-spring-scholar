package com.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scholar.dto.ActivityDTO;
import com.scholar.model.Activity;
import com.scholar.openapi.controller.ActivityControllerOpenApi;
import com.scholar.request.ActivityRequest;
import com.scholar.service.ActivityService;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/activity")
public class ActivityController implements ActivityControllerOpenApi {

	private ActivityService service;
	private BaseController<Activity, 
		ActivityDTO, ActivityRequest> base;
	
	@Autowired
	public ActivityController(ActivityService service) {		
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
	public ResponseEntity<?> save(@RequestBody ActivityRequest request) {
		return base.save(request);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(@PathVariable Long id, 
			@RequestBody ActivityRequest request) {
		
		return base.putById(id, request);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}
	
}
