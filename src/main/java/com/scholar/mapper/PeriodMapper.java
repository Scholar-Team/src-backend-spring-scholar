package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.PeriodDTO;
import com.scholar.model.Period;
import com.scholar.request.PeriodRequest;

@Component
public class PeriodMapper extends BaseMapper<Period, PeriodDTO, PeriodRequest> {

	public PeriodMapper() {
		super(Period.class, PeriodDTO.class, PeriodRequest.class);
	}
}
