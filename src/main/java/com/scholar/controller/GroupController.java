package com.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scholar.dto.GroupDTO;
import com.scholar.model.Group;
import com.scholar.openapi.controller.GroupControllerOpenApi;
import com.scholar.request.GroupRequest;
import com.scholar.service.GroupService;

@RestController
@CrossOrigin
@RequestMapping("/group")
public class GroupController implements GroupControllerOpenApi {

	private GroupService service;
	private BaseController<Group, 
		GroupDTO, GroupRequest> base;
	
	@Autowired
	public GroupController(GroupService service) {		
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
	public ResponseEntity<?> save(@RequestBody GroupRequest request) {
		return base.save(request);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(@PathVariable Long id, 
			@RequestBody GroupRequest request) {
		
		return base.putById(id, request);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}

}
