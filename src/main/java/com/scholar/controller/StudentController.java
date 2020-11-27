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
import com.scholar.dto.StudentDTO;
import com.scholar.model.Student;
import com.scholar.openapi.controller.StudentControllerOpenApi;
import com.scholar.request.StudentRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.security.permissions.CheckDirector;
import com.scholar.security.permissions.CheckStudent;
import com.scholar.service.StudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController implements StudentControllerOpenApi {

	private StudentService service;
	private BaseController<Student, 
	StudentDTO, StudentRequest> base;
	
	@Autowired
	public StudentController(StudentService service) {		
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
	@CheckStudent.ViewData
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckDirector.SaveData
	@PostMapping
	public ResponseEntity<?> save(@RequestBody StudentRequest request) {
		return base.save(request);
	}

	@Override
	@CheckStudent.UpdateData
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(
			@PathVariable Long id,
			@RequestBody StudentRequest request) {
		
		return base.putById(id, request);
	}
	
	@CheckStudent.UpdateData
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody StudentRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckDirector.DeleteData
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}

}
