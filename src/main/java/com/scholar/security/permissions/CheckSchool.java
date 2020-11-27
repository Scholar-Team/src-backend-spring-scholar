package com.scholar.security.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSchool {
	
	String checkSchoolId = "@schoolService.exists(#id) ? "
			+ "@authData.isObjectBelongPerson(@schoolService.findDirectorById(#id)) : true";
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('DI') and "+ checkSchoolId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ViewSchool { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('DI') and "+ checkSchoolId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface UpdateSchool { }
}
