package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.PersonDTO;
import com.scholar.model.Person;
import com.scholar.request.PersonRequest;

@Component
public class PersonMapper extends BaseMapper<Person, PersonDTO, PersonRequest> {

	public PersonMapper() {
		super(Person.class, PersonDTO.class, PersonRequest.class);
	}
}
