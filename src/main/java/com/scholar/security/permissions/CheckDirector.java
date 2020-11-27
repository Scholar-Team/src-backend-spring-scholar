package com.scholar.security.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckDirector {
	
	@PreAuthorize("hasRole('AD') or hasRole('DI')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface SaveData { }
	
	@PreAuthorize("hasRole('AD') or hasRole('DI')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface DeleteData { }
}
