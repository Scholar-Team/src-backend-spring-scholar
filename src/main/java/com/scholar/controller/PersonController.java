package com.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scholar.dto.PersonDTO;
import com.scholar.model.Person;
import com.scholar.openapi.controller.PersonControllerOpenApi;
import com.scholar.request.PersonRequest;
import com.scholar.service.PersonService;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class PersonController implements PersonControllerOpenApi {

	private PersonService service;
	private BaseController<Person, 
	PersonDTO, PersonRequest> base;
	
	@Autowired
	public PersonController(PersonService service) {		
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

}