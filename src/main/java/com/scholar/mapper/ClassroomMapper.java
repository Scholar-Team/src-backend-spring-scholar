package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.ClassroomDTO;
import com.scholar.model.Classroom;
import com.scholar.request.ClassroomRequest;

@Component
public class ClassroomMapper extends BaseMapper<Classroom, ClassroomDTO, ClassroomRequest> {

	public ClassroomMapper() {
		super(Classroom.class, ClassroomDTO.class, ClassroomRequest.class);
	}
}
