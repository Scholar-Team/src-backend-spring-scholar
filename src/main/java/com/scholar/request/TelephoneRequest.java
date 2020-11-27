package com.scholar.request;

import com.scholar.model.enumeration.TelephoneType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelephoneRequest {

	private Long id;
	private String number;
	private TelephoneType type;
}
