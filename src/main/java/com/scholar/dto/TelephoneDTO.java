package com.scholar.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.scholar.model.enumeration.TelephoneType;

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
public class TelephoneDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String number;
	
	@Enumerated(EnumType.STRING)
	private TelephoneType type;
}
