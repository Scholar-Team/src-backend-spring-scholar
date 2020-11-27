package com.scholar.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.scholar.model.enumeration.SchoolType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolRequest {

	private Long id;
	
	@NotBlank
	private String name;
	
	private String site;
	
	@Valid
	private AddressRequest address;
	
	private SchoolType type;
}
