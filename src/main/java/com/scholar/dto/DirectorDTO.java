package com.scholar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"address"})
public class DirectorDTO extends PersonDTO {

	@JsonIgnoreProperties({"director"})
	private SchoolDTO school;
}
