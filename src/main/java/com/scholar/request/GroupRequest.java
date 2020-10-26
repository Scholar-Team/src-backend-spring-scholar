package com.scholar.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.scholar.model.Permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequest {

	private Long id;
	
	@NotBlank
	private String name;
	
	private Set<Permission> permissions;
}
