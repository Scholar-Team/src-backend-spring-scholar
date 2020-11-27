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
import com.scholar.dto.AdministratorDTO;
import com.scholar.model.Administrator;
import com.scholar.openapi.controller.AdministratorControllerOpenApi;
import com.scholar.request.AdministratorRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.service.AdministratorService;

@RestController
@CrossOrigin
@RequestMapping("/administrator")
public class AdministratorController implements AdministratorControllerOpenApi {
	
	private AdministratorService service;
	
	private BaseController<Administrator, 
	AdministratorDTO, AdministratorRequest> base;
	
	@Autowired
	public AdministratorController(AdministratorService service) {	
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
	@CheckAdministrator.ViewAllData
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckAdministrator.SaveData
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AdministratorRequest request) {
		return base.save(request);
	}

	@Override
	@CheckAdministrator.UpdateAllData
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(@PathVariable Long id,
			@RequestBody AdministratorRequest request) {
		
		return base.putById(id, request);
	}
	
	@CheckAdministrator.UpdateAllData
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody AdministratorRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckAdministrator.DeleteData
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}
	
}
