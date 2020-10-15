package com.scholar.request;

import com.scholar.enumerator.SchoolPeriod;
import com.scholar.model.School;

import lombok.Data;

@Data
public class PeriodRequest {
	
	private SchoolPeriod period;
	private School school;
}
