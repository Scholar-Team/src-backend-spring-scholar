package com.scholar.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scholar.model.Director;
import com.scholar.model.Discipline;
import com.scholar.model.Student;
import com.scholar.model.Teacher;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

	@Query("SELECT te "
		+ "FROM Discipline di "
		+ "INNER JOIN di.teachers te "
		+ "WHERE di.id = :id")
	Set<Teacher> findTeachersById(Long id);
	
	@Query("SELECT st "
		+ "FROM Discipline di "
		+ "INNER JOIN di.classrooms cls "
		+ "INNER JOIN cls.students st "
		+ "WHERE di.id = :id")
	Set<Student> findStudentsById(Long id);
	
	@Query("SELECT dir "
		+ "FROM Discipline dis "
		+ "INNER JOIN dis.classrooms cla "
		+ "INNER JOIN cla.period pe "
		+ "INNER JOIN pe.school sc "
		+ "INNER JOIN sc.director dir "
		+ "WHERE dis.id = :id")
	Optional<Director> findDirectorById(Long id);
}
