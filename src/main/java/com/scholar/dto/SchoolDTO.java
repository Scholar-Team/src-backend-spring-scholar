package com.scholar.dto;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scholar.model.enumeration.SchoolType;

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
public class SchoolDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	private String site;
	
	@Enumerated(EnumType.STRING)
	private SchoolType type;
	
	@JsonIgnoreProperties({
		"school",
		"telephones",
		"address",
		"roles",
		"birthDate"
	})
	@ToString.Exclude
	private DirectorDTO director;
	
	@ToString.Exclude
	private Set<PeriodDTO> periods;
	
	@ToString.Exclude
	private AddressDTO address;
}
