package com.scholar.security.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckTelephone {
	
	String checkTelephoneId = "@telephoneService.exists(#id) ? "
			+ "@authData.isObjectBelongPerson(@telephoneService.findPersonById(#id)) : true";
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('US') and "+ checkTelephoneId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ViewTelephone { }
	
	@PreAuthorize("hasRole('AD') or hasRole('US')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface SaveTelephone { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('US') and "+ checkTelephoneId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface UpdateTelephone { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('US') and "+ checkTelephoneId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface DeleteTelephone { }
}
