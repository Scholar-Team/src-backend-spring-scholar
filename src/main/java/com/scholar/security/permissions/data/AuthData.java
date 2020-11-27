package com.scholar.security.permissions.data;

import java.util.Objects;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.scholar.dto.PersonDTO;

@Component
public class AuthData {

	public Authentication getAuthentication() {
		return SecurityContextHolder
				.getContext()
				.getAuthentication();
	}
	
	public Long getPersonId() {		
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("person_id");
	}
	
	public Object getClaim(String claim) {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim(claim);
	}
	
	public Boolean isSuccess(ResponseEntity<?> response) {	
		return response.getStatusCode().is2xxSuccessful();
	}
	
	public Boolean isNull(Object obj) {
		return Objects.isNull(obj);
	}
	
	public Boolean isNonNull(Object obj) {
		return Objects.nonNull(obj);
	}
	
	public Boolean checkTokenId(Long id) {
		return getPersonId().equals(id);
	}
	
	public Boolean isObjectBelongPerson(Optional<PersonDTO> person) {
		return person.isPresent() && 
				person.get().getId().equals(getPersonId());
	}
	
}
