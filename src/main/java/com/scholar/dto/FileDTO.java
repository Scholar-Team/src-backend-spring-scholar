package com.scholar.dto;

import java.net.URL;

import lombok.Data;

@Data
public class FileDTO {

	private Long id;
	private String name;
	private String fullName;
	private String contentType;
	private Long size;
	private URL url;
}
