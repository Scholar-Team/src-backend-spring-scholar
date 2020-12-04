package com.scholar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scholar.model.Class;
import com.scholar.model.School;
import com.scholar.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	@Query("SELECT sc "
		+ "FROM Teacher te "
		+ "INNER JOIN te.disciplines dis "
		+ "INNER JOIN dis.classrooms cla "
		+ "INNER JOIN cla.period pe "
		+ "INNER JOIN pe.school sc "
		+ "WHERE te.id = :id")
	Optional<School> findSchoolById(Long id);
	
	@Query("SELECT cl "
		+ "FROM Teacher te "
		+ "INNER JOIN te.disciplines dis "
		+ "INNER JOIN dis.classes cl "
		+ "WHERE te.id = :id")
	List<Class> findClassesById(Long id);
}
