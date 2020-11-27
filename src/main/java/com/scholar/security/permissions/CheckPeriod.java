package com.scholar.security.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckPeriod {
	
	String checkPeriodId = "@periodService.exists(#id) ? "
			+ "@authData.isObjectBelongPerson(@periodService.findDirectorById(#id)) : true";
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('DI') and "+ checkPeriodId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ViewPeriod { }
	
	@PreAuthorize("hasRole('AD') or hasRole('DI')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface SavePeriod { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('DI') and "+ checkPeriodId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface UpdatePeriod { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('DI') and "+ checkPeriodId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface DeletePeriod { }
}
