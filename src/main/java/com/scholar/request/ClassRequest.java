package com.scholar.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassRequest {

	private Long id;
	private String name;
	private FileRequest attachment;
	private String description;
	private String link;
	private DisciplineRequest discipline;
}
