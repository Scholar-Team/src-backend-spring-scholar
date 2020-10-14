package com.scholar.request;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.scholar.validation.FileSize;

import lombok.Data;

@Data
public class FileRequest {

	@NotNull
	@FileSize(max = "50MB")
	private MultipartFile file;
}
