package com.scholar.dto;

import java.net.URL;

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
public class FileDTO {

	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	private String fullName;
	private String contentType;
	private Long size;
	private URL url;
}
