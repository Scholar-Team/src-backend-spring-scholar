package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.TelephoneDTO;
import com.scholar.model.Telephone;
import com.scholar.request.TelephoneRequest;

@Component
public class TelephoneMapper extends BaseMapper<Telephone, TelephoneDTO, TelephoneRequest> {

	public TelephoneMapper() {
		super(Telephone.class, TelephoneDTO.class, TelephoneRequest.class);
	}
}
