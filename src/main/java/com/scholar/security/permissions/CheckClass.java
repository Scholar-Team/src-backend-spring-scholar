package com.scholar.security.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckClass {
		
	String checkDisciplineId = "@authData.isNonNull(#request.discipline) ? "
			+ "@disciplineService.exists(#request.discipline.id) ? "
			+ "@classData.isClassBelongTeacher("
				+ "@disciplineService.findTeachersById(#request.discipline.id)"
			+ ") : true : true";
		
	String checkIfItsTeacher = "@classService.exists(#id) ? "
			+ "@classData.isClassBelongTeacher(@classService.findTeachersById(#id)) : true";
	
	String checkIfItsStudent = "@classService.exists(#id) ? "
			+ "@classData.isClassBelongStudent(@classService.findStudentsById(#id)) : true";
	
	@PreAuthorize("hasRole('AD') or "
			+ "hasRole('DI') or "
			+ "(hasRole('PR') and "+ checkIfItsTeacher +") and "
			+ "(hasRole('AL') and "+ checkIfItsStudent +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ViewClass { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "hasRole('DI') or "
			+ "(hasRole('PR') and "+ checkDisciplineId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface SaveClass { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "hasRole('DI') or "
			+ "(hasRole('PR') and "+ checkDisciplineId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface UpdateClass { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "hasRole('DI') or "
			+ "(hasRole('PR') and "+ checkIfItsTeacher +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface DeleteClass { }
}
