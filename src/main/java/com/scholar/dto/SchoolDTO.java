package com.scholar.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scholar.enumerator.SchoolType;

import lombok.Data;

@Data
public class SchoolDTO {

	private Long id;
	private String name;
	private String site;
	private SchoolType type;
	
	@JsonIgnoreProperties({"school", "telephones", "address"})
	private DirectorDTO director;
	
	private List<PeriodDTO> periods;
	
	private AddressDTO address;
}
