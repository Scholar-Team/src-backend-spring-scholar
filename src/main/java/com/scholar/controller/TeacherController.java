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
import com.scholar.dto.TeacherDTO;
import com.scholar.model.Teacher;
import com.scholar.openapi.controller.TeacherControllerOpenApi;
import com.scholar.request.FileRequest;
import com.scholar.request.TeacherRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.security.permissions.CheckDirector;
import com.scholar.security.permissions.CheckTeacher;
import com.scholar.service.TeacherService;

@RestController
@CrossOrigin
@RequestMapping("/teacher")
public class TeacherController implements TeacherControllerOpenApi {

	private TeacherService service;
	private BaseController<Teacher, 
		TeacherDTO, TeacherRequest> base;
	
	@Autowired
	public TeacherController(TeacherService service) {		
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
	@CheckTeacher.ViewData
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckDirector.SaveData
	@PostMapping
	public ResponseEntity<?> save(
			@RequestPart(name = "teacher", required = true) TeacherRequest request,
			FileRequest fileRequest) {
		
		request.setFile(fileRequest);
		return base.save(request);
	}

	@Override
	@CheckTeacher.UpdateData
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(
			@PathVariable Long id,
			@RequestBody TeacherRequest request) {
		return base.putById(id, request);
	}
	
	@CheckTeacher.UpdateData
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody TeacherRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckDirector.DeleteData
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}

}
