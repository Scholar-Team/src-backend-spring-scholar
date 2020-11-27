package com.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scholar.controller.generic.BaseController;
import com.scholar.dto.PeriodDTO;
import com.scholar.model.Period;
import com.scholar.openapi.controller.PeriodControllerOpenApi;
import com.scholar.request.PeriodRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.security.permissions.CheckPeriod;
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
	@CheckAdministrator.ViewAllData
	@GetMapping
	public ResponseEntity<?> findAll() {
		return base.findAll();
	}

	@Override
	@CheckPeriod.ViewPeriod
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckPeriod.SavePeriod
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PeriodRequest request) {
		return base.save(request);
	}

	@Override
	@CheckPeriod.UpdatePeriod
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(
			@PathVariable Long id,
			@RequestBody PeriodRequest request) {
		return base.putById(id, request);
	}
	
	@CheckPeriod.UpdatePeriod
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody PeriodRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckPeriod.DeletePeriod
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}
	
	@CheckPeriod.ViewPeriod
	@GetMapping("/{id}/classrooms")
	public ResponseEntity<?> findClassroomsById(@PathVariable Long id) {
		return base.findListById(service.findClassroomsById(id)); 
	}
	
	@CheckPeriod.ViewPeriod
	@GetMapping("/{id}/students")
	public ResponseEntity<?> findStudentsById(@PathVariable Long id) {
		return base.findListById(service.findStudentsById(id)); 
	}
	
	@CheckPeriod.ViewPeriod
	@GetMapping("/{id}/disciplines")
	public ResponseEntity<?> findDisciplinesById(@PathVariable Long id) {
		return base.findListById(service.findDisciplinesById(id)); 
	}
	
	@CheckPeriod.ViewPeriod
	@GetMapping("/{id}/teachers")
	public ResponseEntity<?> findTeachersById(@PathVariable Long id) {
		return base.findListById(service.findTeachersById(id)); 
	}
	
}
