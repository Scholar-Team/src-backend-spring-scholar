package com.scholar.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scholar.dto.FileDTO;
import com.scholar.mapper.FileMapper;
import com.scholar.model.File;
import com.scholar.repository.FileRepository;
import com.scholar.request.FileRequest;

@Service
public class FileService extends BaseService<File, FileDTO, FileRequest> {

	private FileRepository repository;
	private FileMapper mapper;
	private S3FotoStorageService s3Service;
	
	@Autowired
	public FileService(FileRepository repository, 
			FileMapper mapper, S3FotoStorageService s3Service) {
		super(repository, mapper);
		
		this.repository = repository;
		this.mapper = mapper;
		this.s3Service = s3Service;
	}
	
	@Override
	public Optional<FileDTO> save(FileRequest request) {
		MultipartFile file = request.getFile();
		File fileModel = mapper.requestToModel(request);
		
		fileModel.setFullName(UUID.randomUUID()
				.toString()
				.concat("_")
				.concat(file.getOriginalFilename()));
		
		fileModel.setName(file.getOriginalFilename());
		fileModel.setContentType(file.getContentType());
		fileModel.setSize(file.getSize());
		fileModel.setUrl(s3Service.save(file, fileModel.getFullName()));
	    
	    return Optional.of(mapper.modelToDTO(repository.saveAndFlush(fileModel)));
	}
	
	@Override
	public void deleteById(Long id) {
		s3Service.remove(repository.findById(id).get().getFullName());
		super.deleteById(id);
	}

}
