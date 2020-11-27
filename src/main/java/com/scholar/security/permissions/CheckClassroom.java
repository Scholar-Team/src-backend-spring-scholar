package com.scholar.security.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckClassroom {
		
	String checkPeriodId = "@authData.isNonNull(#request.period) ? "
			+ "@periodService.exists(#request.period.id) ? "
			+ "@authData.isObjectBelongPerson("
				+ "@periodService.findDirectorById(#request.period.id)"
			+ ") : true : true";
	
	String checkClassroomId = "@classroomService.exists(#id) ? "
			+ "@authData.isObjectBelongPerson(@classroomService.findDirectorById(#id)) : true";
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('DI') and "+ checkClassroomId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ViewClassroom { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('DI') and "+ checkPeriodId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface SaveClassroom { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('DI') and "+ checkPeriodId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface UpdateClassroom { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "(hasRole('DI') and "+ checkClassroomId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface DeleteClassroom { }
}
