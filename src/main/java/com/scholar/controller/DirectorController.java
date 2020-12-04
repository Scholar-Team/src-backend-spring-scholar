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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.scholar.controller.generic.BaseController;
import com.scholar.dto.DirectorDTO;
import com.scholar.model.Director;
import com.scholar.openapi.controller.DirectorControllerOpenApi;
import com.scholar.request.DirectorRequest;
import com.scholar.request.FileRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.security.permissions.CheckPerson;
import com.scholar.service.DirectorService;

@RestController
@CrossOrigin
@RequestMapping("/director")
public class DirectorController implements DirectorControllerOpenApi {

	private DirectorService service;
	private BaseController<Director, 
		DirectorDTO, DirectorRequest> base;
	
	@Autowired
	public DirectorController(DirectorService service) {		
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
	@CheckPerson.ViewData
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	//@CheckAdministrator.SaveData
	@PostMapping
	public ResponseEntity<?> save(
			@RequestPart(name = "director", required = true) DirectorRequest request,
			FileRequest fileRequest) {
		
		request.setFile(fileRequest);
		return base.save(request);
	}

	@Override
	@CheckPerson.UpdateData
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(
			@PathVariable Long id, 
			@RequestBody DirectorRequest request) {
		
		return base.putById(id, request);
	}
	
	@CheckPerson.UpdateData
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody DirectorRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckPerson.DeleteData
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}

}
