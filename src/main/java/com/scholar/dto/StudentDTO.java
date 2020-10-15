package com.scholar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentDTO extends PersonDTO {

	@JsonIgnoreProperties({"students", "disciplines"})
	private ClassroomDTO classroom;
}
