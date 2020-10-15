package com.scholar.request;

import com.scholar.model.Classroom;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentRequest extends PersonRequest {

	private Classroom classroom;
}
