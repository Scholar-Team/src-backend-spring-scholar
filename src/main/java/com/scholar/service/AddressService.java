package com.scholar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholar.dto.AddressDTO;
import com.scholar.mapper.AddressMapper;
import com.scholar.model.Address;
import com.scholar.repository.AddressRepository;
import com.scholar.request.AddressRequest;

@Service
public class AddressService extends BaseService<Address, AddressDTO, AddressRequest> {

	private AddressRepository repository;
	
	@Autowired
	public AddressService(AddressRepository repository, AddressMapper mapper) {
		super(repository, mapper);
		
		this.repository = repository;
	}
}
