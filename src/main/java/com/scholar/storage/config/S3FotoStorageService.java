package com.scholar.storage.config;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3FotoStorageService {
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;
	
	public URL save(MultipartFile file, String nameFile) {
		try {
			String filePath = getFilePath(nameFile);
			
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(file.getContentType());
			
			PutObjectRequest putObjectRequest = new PutObjectRequest(
					storageProperties.getBucket(),
					filePath,
					file.getInputStream(),
					objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
			
			amazonS3.putObject(putObjectRequest);
			
			return amazonS3.getUrl(storageProperties.getBucket(), filePath);
			
		} catch (Exception e) { }
		
		return null;				
	}

	private String getFilePath(String fileName) {
		return String.format("%s/%s", storageProperties.getDirectory(), fileName);
	}
	
	public void remove(String fileName) {
		try {
			String filePath = getFilePath(fileName);
			
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
					storageProperties.getBucket(), filePath);
			
			amazonS3.deleteObject(deleteObjectRequest);
			
		} catch (Exception e) {
			//throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
			throw e;
		}
	}
	
}
