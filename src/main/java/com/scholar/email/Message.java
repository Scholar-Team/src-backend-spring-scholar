package com.scholar.email;

import java.util.Map;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

@Builder
@Data
public class Message {
	
	@Singular
	private Set<String> recipients;
	
	@NonNull
	private String matter;
	
	@NonNull
	private String body;
	
	@Singular
	private Map<String, Object> variables;
	
}
