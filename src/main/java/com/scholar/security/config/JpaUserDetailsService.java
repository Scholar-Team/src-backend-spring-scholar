package com.scholar.security.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scholar.model.Person;
import com.scholar.repository.PersonRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private PersonRepository personRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personRepository.findByEmail(username)
			.orElseThrow(() -> 
				new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));
		
		return new AuthUser(person, getAuthorities(person));
	}
	
	private Collection<GrantedAuthority> getAuthorities(Person person) {		
		return person.getGroups().stream()
			.flatMap(group -> group.getPermissions().stream())
			.map(permission -> new SimpleGrantedAuthority(permission.getName().toUpperCase()))
			.collect(Collectors.toSet());
	}

}
