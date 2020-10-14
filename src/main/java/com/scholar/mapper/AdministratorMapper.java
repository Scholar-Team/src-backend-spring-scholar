package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.AdministratorDTO;
import com.scholar.model.Administrator;
import com.scholar.request.AdministratorRequest;

@Component
public class AdministratorMapper 
	extends BaseMapper<Administrator, AdministratorDTO, AdministratorRequest> {

	public AdministratorMapper() {
		super(Administrator.class, AdministratorDTO.class, AdministratorRequest.class);
	}
}
