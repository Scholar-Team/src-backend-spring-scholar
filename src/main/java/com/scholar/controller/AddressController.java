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
import com.scholar.dto.AddressDTO;
import com.scholar.model.Address;
import com.scholar.openapi.controller.AddressControllerOpenApi;
import com.scholar.request.AddressRequest;
import com.scholar.security.permissions.CheckAddress;
import com.scholar.security.permissions.CheckAdministrator;
import com.scholar.service.AddressService;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController implements AddressControllerOpenApi {

	private AddressService service;
	private BaseController<Address, 
		AddressDTO, AddressRequest> base;
	
	@Autowired
	public AddressController(AddressService service) {		
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
	@CheckAddress.ViewAddress
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return base.findById(id);
	}

	@Override
	@CheckAddress.SaveAddress
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AddressRequest request) {
		return base.save(request);
	}

	@Override
	@CheckAddress.UpdateAddress
	@PutMapping("/{id}")
	public ResponseEntity<?> putById(@PathVariable Long id,
			@RequestBody AddressRequest request) {
		
		return base.putById(id, request);
	}
	
	@CheckAddress.UpdateAddress
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchById(
			@PathVariable Long id, 
			@RequestBody AddressRequest request) {
		
		return base.patchById(id, request);
	}

	@Override
	@CheckAddress.DeleteAddress
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return base.deleteById(id);
	}

}
