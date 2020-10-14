package com.scholar.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("scholar.storage.s3")
public class StorageProperties {

	private String idAccessKey;
	private String secretAccessKey;
	private String bucket;
	private Regions region;
	private String directory;
}
