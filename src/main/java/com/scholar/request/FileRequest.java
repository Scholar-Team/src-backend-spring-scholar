package com.scholar.request;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.scholar.validation.FileSize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileRequest {

	private Long id;
	
	@NotNull
	@FileSize(max = "100MB")
	private MultipartFile file;
}
