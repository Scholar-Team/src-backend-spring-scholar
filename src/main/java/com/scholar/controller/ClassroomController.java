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
import com.scholar.dto.ClassroomDTO;
import com.scholar.model.Classroom;
import com.scholar.openapi.controller.ClassroomControllerOpenApi;
import com.scholar.request.ClassroomRequest;
import com.scholar.request.FileRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.security.permissions.CheckClassroom;
import com.scholar.service.ClassroomService;

@RestController
@CrossOrigin
@RequestMapping("/classroom")
public class ClassroomController implements ClassroomControllerOpenApi {
	
	private ClassroomService service;
	private BaseController<Classroom, 
	ClassroomDTO, ClassroomRequest> base;
	
	@Autowired
	public ClassroomController(ClassroomService service) {		
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
	@CheckClassroom.ViewClassroom
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckClassroom.SaveClassroom
	@PostMapping
	public ResponseEntity<?> save(
			@RequestPart(name = "classroom", required = true) ClassroomRequest request,
			FileRequest fileRequest) {
		
		request.setFile(fileRequest);
		return base.save(request);
	}

	@Override
	@CheckClassroom.UpdateClassroom
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(
			@PathVariable Long id,
			@RequestBody ClassroomRequest request) {
		
		return base.putById(id, request);
	}
	
	@CheckClassroom.UpdateClassroom
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody ClassroomRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckClassroom.DeleteClassroom
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}

}
