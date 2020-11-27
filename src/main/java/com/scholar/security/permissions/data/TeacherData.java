package com.scholar.security.permissions.data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scholar.dto.SchoolDTO;

@Component
public class TeacherData {
	
	@Autowired
	private AuthData data;
	
	public Boolean isDirectorBelongSchool(Optional<SchoolDTO> school) {
		return school.isPresent() && school.get().getDirector().getId().equals(data.getPersonId());
	}
}
