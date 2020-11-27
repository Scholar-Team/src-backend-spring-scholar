package com.scholar.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.scholar.dto.FileDTO;
import com.scholar.mapper.FileMapper;
import com.scholar.model.File;
import com.scholar.repository.FileRepository;
import com.scholar.request.FileRequest;
import com.scholar.security.permissions.data.AuthData;
import com.scholar.service.generic.BaseService;
import com.scholar.storage.config.S3FotoStorageService;

@Service
public class FileService
	extends BaseService<File, FileDTO, FileRequest> {

	private FileRepository repository;
	private FileMapper mapper;
	
	private S3FotoStorageService s3Service;
	
	@Autowired
	public FileService(
			FileRepository repository, 
			FileMapper mapper,
			S3FotoStorageService s3Service,
			AuthData authData) {
		super(repository, mapper, authData);
		
		this.repository = repository;
		this.mapper = mapper;
		this.s3Service = s3Service;
	}
	
	@Override
	@Transactional
	public Optional<FileDTO> save(FileRequest request) {
		MultipartFile multiPartFile = request.getFile();
		File fileModel = mapper.requestToModel(request);
		
		fileModel.setFullName(UUID.randomUUID()
				.toString()
				.concat("_")
				.concat(multiPartFile.getOriginalFilename()));
		
		fileModel.setName(multiPartFile.getOriginalFilename());
		fileModel.setContentType(multiPartFile.getContentType());
		fileModel.setSize(multiPartFile.getSize());
		fileModel.setUrl(s3Service.save(multiPartFile, fileModel.getFullName()));
	    
	    return Optional.of(mapper.modelToDTO(repository.saveAndFlush(fileModel)));
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		s3Service.remove(repository.findById(id).get().getFullName());
		super.deleteById(id);
	}

}
