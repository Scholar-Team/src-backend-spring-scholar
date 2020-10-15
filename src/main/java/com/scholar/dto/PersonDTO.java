package com.scholar.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

	private Long id;
	private String name;	
	private LocalDate birthDate;
	private String cpf;
	private List<TelephoneDTO> telephones;
	private AddressDTO address;
}
