package com.scholar.security.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckPerson {

	String checkPersonId = "@personService.exists(#id) ? "
			+ "@authData.checkTokenId(#id) : true";
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasAuthority('US01') and "+ checkPersonId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ViewData { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasAuthority('US02') and "+ checkPersonId + ")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface UpdateData { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasAuthority('US03') and "+ checkPersonId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface DeleteData { }
}
