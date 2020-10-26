package com.scholar.security.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.scholar.model.Person;

import lombok.Getter;

@Getter
public class AuthUser extends User {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public AuthUser(Person person, Collection<? extends GrantedAuthority> permissoes) {
		super(person.getEmail(), person.getPassword(), permissoes);
		
		this.id = person.getId();
		this.name = person.getName();
	}
	
}
