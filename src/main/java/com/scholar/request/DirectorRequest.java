package com.scholar.request;

import com.scholar.model.School;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DirectorRequest extends PersonRequest {

	private School school;
}
