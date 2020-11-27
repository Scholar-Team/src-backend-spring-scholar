package com.scholar.security.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckAddress {
	
	String checkAddressId = "@addressService.exists(#id) ? "
			+ "@authData.isObjectBelongPerson(@addressService.findPersonById(#id)) : true";
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('US') and "+ checkAddressId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ViewAddress { }
	
	@PreAuthorize("hasRole('AD') or hasRole('US')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface SaveAddress { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('US') and "+ checkAddressId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface UpdateAddress { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('US') and "+ checkAddressId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface DeleteAddress { }
}
