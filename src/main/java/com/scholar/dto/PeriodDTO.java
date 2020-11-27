package com.scholar.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scholar.model.enumeration.SchoolPeriod;

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
public class PeriodDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private SchoolPeriod period;
	
	@JsonIgnoreProperties({
		"period"
	})
	@ToString.Exclude
	private Set<ClassroomDTO> classrooms;
}
