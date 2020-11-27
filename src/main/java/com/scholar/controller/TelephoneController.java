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
import com.scholar.dto.TelephoneDTO;
import com.scholar.model.Telephone;
import com.scholar.openapi.controller.TelephoneControllerOpenApi;
import com.scholar.request.TelephoneRequest;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.security.permissions.CheckTelephone;
import com.scholar.service.TelephoneService;

@RestController
@CrossOrigin
@RequestMapping("/telephone")
public class TelephoneController implements TelephoneControllerOpenApi {

	private TelephoneService service;
	
	private BaseController<Telephone, 
	TelephoneDTO, TelephoneRequest> base;
	
	@Autowired
	public TelephoneController(TelephoneService service) {		
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
	@CheckTelephone.ViewTelephone
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckTelephone.SaveTelephone
	@PostMapping
	public ResponseEntity<?> save(@RequestBody TelephoneRequest request) {
		return base.save(request);
	}

	@Override
	@CheckTelephone.UpdateTelephone
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(
			@PathVariable Long id,
			@RequestBody TelephoneRequest request) {
		
		return base.putById(id, request);
	}
	
	@CheckTelephone.UpdateTelephone
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody TelephoneRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckTelephone.DeleteTelephone
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}

}
