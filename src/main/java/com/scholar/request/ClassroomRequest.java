package com.scholar.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomRequest {

	private Long id;
	private String name;
	private PeriodRequest period;
	private FileRequest file;
}
