package com.scholar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scholar.model.Classroom;
import com.scholar.model.Director;
import com.scholar.model.Discipline;
import com.scholar.model.Period;
import com.scholar.model.Student;
import com.scholar.model.Teacher;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

	@Query("SELECT di "
		+ "FROM Period pe "
		+ "INNER JOIN pe.school sc "
		+ "INNER JOIN sc.director di "
		+ "WHERE pe.id = :id")
	Optional<Director> findDirectorById(Long id);
	
	@Query("SELECT cl "
		+ "FROM Period pe "
		+ "INNER JOIN pe.classrooms cl "
		+ "WHERE pe.id = :id")
	List<Classroom> findClassroomsById(Long id);
	
	@Query("SELECT st "
		+ "FROM Period pe "
		+ "INNER JOIN pe.classrooms cl "
		+ "INNER JOIN cl.students st "
		+ "WHERE pe.id = :id")
	List<Student> findStudentsById(Long id);
	
	@Query("SELECT di "
		+ "FROM Period pe "
		+ "INNER JOIN pe.classrooms cl "
		+ "INNER JOIN cl.disciplines di "
		+ "WHERE pe.id = :id")
	List<Discipline> findDisciplinesById(Long id);
	
	@Query("SELECT te "
		+ "FROM Period pe "
		+ "INNER JOIN pe.classrooms cl "
		+ "INNER JOIN cl.disciplines di "
		+ "INNER JOIN di.teachers te "
		+ "WHERE pe.id = :id")
	List<Teacher> findTeachersById(Long id);
}
