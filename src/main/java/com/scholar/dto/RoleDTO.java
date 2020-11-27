package com.scholar.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class RoleDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	
	@JsonIgnoreProperties({
		"description"
	})
	@ToString.Exclude
	private Set<PermissionDTO> permissions;
}
