package com.scholar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String cep;
	private String state;
	private String city;
	private String street;
	private String number;
	private String complement;
}
