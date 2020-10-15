package com.scholar.request;

import com.scholar.model.Period;

import lombok.Data;

@Data
public class ClassroomRequest {

	private String name;
	private Period period;
}
