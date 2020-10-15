package com.scholar.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddressRequest {

	@NotBlank
	private String cep;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String street;
	
	@NotBlank
	private String number;
	
	private String complement;
}
