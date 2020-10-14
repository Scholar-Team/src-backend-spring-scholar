package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.ClassDTO;
import com.scholar.model.Class;
import com.scholar.request.ClassRequest;

@Component
public class ClassMapper extends BaseMapper<Class, ClassDTO, ClassRequest> {

	public ClassMapper() {
		super(Class.class, ClassDTO.class, ClassRequest.class);
	}
}
