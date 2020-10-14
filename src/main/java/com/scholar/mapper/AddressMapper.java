package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.AddressDTO;
import com.scholar.model.Address;
import com.scholar.request.AddressRequest;

@Component
public class AddressMapper extends BaseMapper<Address, AddressDTO, AddressRequest> {

	public AddressMapper() {
		super(Address.class, AddressDTO.class, AddressRequest.class);
	}
}
