package com.scholar.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	private String email;
	private LocalDate birthDate;
	private String cpf;
	
	@ToString.Exclude
	private Set<TelephoneDTO> telephones;
	
	@ToString.Exclude
	private Set<RoleDTO> roles;
	
	private AddressDTO address;
}
