package com.scholar.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.scholar.enumerator.SchoolType;

import lombok.Data;

@Data
public class SchoolRequest {

	private Long id;
	
	@NotBlank
	private String name;
	
	private String site;
	
	@Valid
	private AddressRequest address;
	
	private SchoolType type;
}
