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
import com.scholar.dto.ClassDTO;
import com.scholar.model.Class;
import com.scholar.openapi.controller.ClassControllerOpenApi;
import com.scholar.request.ClassRequest;
import com.scholar.request.FileRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.security.permissions.CheckClass;
import com.scholar.service.ClassService;

@RestController
@CrossOrigin
@RequestMapping("/class")
public class ClassController implements ClassControllerOpenApi {

	private ClassService service;
	private BaseController<Class, 
		ClassDTO, ClassRequest> base;
	
	@Autowired
	public ClassController(ClassService service) {		
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
	@CheckClass.ViewClass
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckClass.SaveClass
	@PostMapping
	public ResponseEntity<?> save(
			@RequestPart(name = "class", required = true) ClassRequest request,
			FileRequest attachment) {
		
		request.setAttachment(attachment);
		return base.save(request);
	}

	@Override
	@CheckClass.UpdateClass
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(@PathVariable Long id,
			@RequestBody ClassRequest request) {
		
		return base.putById(id, request);
	}
	
	@CheckClass.UpdateClass
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody ClassRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckClass.DeleteClass
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}
	
	@CheckClass.ViewClass
	@GetMapping("/{id}/teachers")
	public ResponseEntity<?> findTeachersById(@PathVariable Long id) {
		return base.findListById(service.findTeachersById(id));
	}
	
	@CheckClass.ViewClass
	@GetMapping("/{id}/students")
	public ResponseEntity<?> findStudentsById(@PathVariable Long id) {
		return base.findListById(service.findStudentsById(id));
	}
	
}
