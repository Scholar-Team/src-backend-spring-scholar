package com.scholar.request;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.scholar.model.Class;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

	private Long id;
	private String name;
	private FileRequest attachment;
	private String description;
	private ZonedDateTime finalDate;
	private ClassroomRequest classroom;
	
	@Builder.Default
	private Set<Class> classes = new HashSet<>();
}
