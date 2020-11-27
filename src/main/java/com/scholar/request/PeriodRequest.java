package com.scholar.request;

import com.scholar.model.enumeration.SchoolPeriod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodRequest {
	
	private Long id;
	private SchoolPeriod period;
	private SchoolRequest school;
}
