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
import com.scholar.dto.SchoolDTO;
import com.scholar.model.School;
import com.scholar.openapi.controller.SchoolControllerOpenApi;
import com.scholar.request.SchoolRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.security.permissions.CheckSchool;
import com.scholar.service.SchoolService;

@RestController
@CrossOrigin
@RequestMapping("/school")
public class SchoolController implements SchoolControllerOpenApi {

	private SchoolService service;
	private BaseController<School, 
		SchoolDTO, SchoolRequest> base;
	
	@Autowired
	public SchoolController(SchoolService service) {		
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
	@CheckSchool.ViewSchool
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckAdministrator.SaveData
	@PostMapping
	public ResponseEntity<?> save(@RequestBody SchoolRequest request) {
		return base.save(request);
	}

	@Override
	@CheckSchool.UpdateSchool
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(
			@PathVariable Long id,
			@RequestBody SchoolRequest request) {
		
		return base.putById(id, request);
	}
	
	@CheckSchool.UpdateSchool
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody SchoolRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckAdministrator.DeleteData
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}
	
	@CheckSchool.ViewSchool
	@GetMapping("/{id}/periods")
	public ResponseEntity<?> findPeriodsById(@PathVariable Long id) {
		return base.findListById(service.findPeriodsById(id));
	}
	
	@CheckSchool.ViewSchool
	@GetMapping("/{id}/students")
	public ResponseEntity<?> findStudentsById(@PathVariable Long id) {
		return base.findListById(service.findStudentsById(id)); 
	}
	
	@CheckSchool.ViewSchool
	@GetMapping("/{id}/classrooms")
	public ResponseEntity<?> findClassroomsById(@PathVariable Long id) {
		return base.findListById(service.findClassroomsById(id)); 
	}
	
	@CheckSchool.ViewSchool
	@GetMapping("/{id}/teachers")
	public ResponseEntity<?> findTeachersById(@PathVariable Long id) {
		return base.findListById(service.findTeachersById(id)); 
	}
	
	@CheckSchool.ViewSchool
	@GetMapping("/{id}/disciplines")
	public ResponseEntity<?> findDisciplinesById(@PathVariable Long id) {
		return base.findListById(service.findDisciplinesById(id)); 
	}

}
