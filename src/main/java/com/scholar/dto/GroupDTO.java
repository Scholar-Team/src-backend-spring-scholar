package com.scholar.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

	private Long id;
	private String name;
	private Set<PermissionDTO> permissions;
}
