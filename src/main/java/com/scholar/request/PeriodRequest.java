package com.scholar.request;

import com.scholar.model.School;
import com.scholar.model.enumeration.SchoolPeriod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodRequest {
	
	private SchoolPeriod period;
	private School school;
}
