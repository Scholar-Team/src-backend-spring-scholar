package com.scholar.security.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckDiscipline {
		
	String checkDisciplineId = "@authData.isNonNull(#request.discipline) ? "
			+ "@disciplineService.exists(#request.discipline.id) ? "
			+ "@DisciplineData.isDisciplineBelongTeacher("
				+ "@disciplineService.findTeachersById(#request.discipline.id)"
			+ ") : true : true";
	
	String checkDirectorId = "true";
		
	String checkIfItsTeacher = "@disciplineService.exists(#id) ? "
			+ "@disciplineData.isDisciplineBelongTeacher(@disciplineService.findTeachersById(#id)) : true";
	
	String checkIfItsStudent = "@disciplineService.exists(#id) ? "
			+ "@disciplineData.isDisciplineBelongStudent(@disciplineService.findStudentsById(#id)) : true";
	
	@PreAuthorize("hasRole('AD') or "
			+ "hasRole('DI') or "
			+ "(hasRole('PR') and "+ checkIfItsTeacher +") and "
			+ "(hasRole('AL') and "+ checkIfItsStudent +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ViewDiscipline { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "hasRole('DI') or "
			+ "(hasRole('PR') and "+ checkDirectorId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface SaveDiscipline { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "hasRole('DI') or "
			+ "(hasRole('PR') and "+ checkDirectorId +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface UpdateDiscipline { }
	
	@PreAuthorize("hasRole('AD') or "
			+ "hasRole('DI') or "
			+ "(hasRole('PR') and "+ checkIfItsTeacher +")")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface DeleteDiscipline { }
}
