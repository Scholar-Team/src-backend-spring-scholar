package com.scholar.dto;

import lombok.Data;

@Data
public class AddressDTO {

	private Long id;
	private String cep;
	private String state;
	private String city;
	private String street;
	private String number;
	private String complement;
}
