package com.scholar.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scholar.model.Class;
import com.scholar.model.Discipline;
import com.scholar.model.Student;
import com.scholar.model.Teacher;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

	@Query("SELECT di "
		+ "FROM Class cl "
		+ "INNER JOIN cl.discipline di "
		+ "WHERE cl.id = :id")
	Optional<Discipline> findDisciplineById(Long id);
	
	@Query("SELECT te "
		+ "FROM Class cl "
		+ "INNER JOIN cl.discipline di "
		+ "INNER JOIN di.teachers te "
		+ "WHERE cl.id = :id")
	Set<Teacher> findTeachersById(Long id);
	
	@Query("SELECT st "
		+ "FROM Class cl "
		+ "INNER JOIN cl.discipline di "
		+ "INNER JOIN di.classrooms cls "
		+ "INNER JOIN cls.students st "
		+ "WHERE cl.id = :id")
	Set<Student> findStudentsById(Long id);
}
