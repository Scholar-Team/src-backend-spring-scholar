package com.scholar.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest {

	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
}
