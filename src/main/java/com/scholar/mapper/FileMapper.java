package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.FileDTO;
import com.scholar.model.File;
import com.scholar.request.FileRequest;

@Component
public class FileMapper extends BaseMapper<File, FileDTO, FileRequest> {

	public FileMapper() {
		super(File.class, FileDTO.class, FileRequest.class);
	}
}
