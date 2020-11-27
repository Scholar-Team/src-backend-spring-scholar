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
import com.scholar.dto.DisciplineDTO;
import com.scholar.model.Discipline;
import com.scholar.openapi.controller.DisciplineControllerOpenApi;
import com.scholar.request.DisciplineRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.security.permissions.CheckDiscipline;
import com.scholar.service.DisciplineService;

@RestController
@CrossOrigin
@RequestMapping("/discipline")
public class DisciplineController implements DisciplineControllerOpenApi {
	
	private DisciplineService service;
	private BaseController<Discipline, 
	DisciplineDTO, DisciplineRequest> base;
	
	@Autowired
	public DisciplineController(DisciplineService service) {		
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
	@CheckDiscipline.ViewDiscipline
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckDiscipline.SaveDiscipline
	@PostMapping
	public ResponseEntity<?> save(@RequestBody DisciplineRequest request) {
		return base.save(request);
	}

	@Override
	@CheckDiscipline.UpdateDiscipline
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(
			@PathVariable Long id,
			@RequestBody DisciplineRequest request) {
		
		return base.putById(id, request);
	}
	
	@CheckDiscipline.UpdateDiscipline
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody DisciplineRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckDiscipline.DeleteDiscipline
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}

}
