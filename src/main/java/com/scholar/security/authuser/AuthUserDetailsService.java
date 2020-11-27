package com.scholar.security.authuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class AuthUserDetailsService implements UserDetailsService {

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
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		person.getRoles().stream()
			.flatMap(role -> role.getPermissions().stream())
			.forEach(p -> {
			GrantedAuthority authority = 
					new SimpleGrantedAuthority(p.getName().toUpperCase());
			
			authorities.add(authority);
		});
	
		person.getRoles().stream().forEach(r -> {
			GrantedAuthority authority = 
					new SimpleGrantedAuthority("ROLE_".concat((r.getName()
							.substring(0, 2))
							.toUpperCase()));
			
			authorities.add(authority);
		});
	
		return authorities;
	}

}
