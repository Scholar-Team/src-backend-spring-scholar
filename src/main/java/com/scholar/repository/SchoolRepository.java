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
import com.scholar.model.School;
import com.scholar.model.Student;
import com.scholar.model.Teacher;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

	@Query("SELECT di "
		+ "FROM School sc "
		+ "INNER JOIN sc.director di "
		+ "WHERE sc.id = :id")
	Optional<Director> findDirectorById(Long id);
	
	@Query("SELECT pe "
		+ "FROM School sc "
		+ "INNER JOIN sc.periods pe "
		+ "WHERE sc.id = :id")
	List<Period> findPeriodsById(Long id);
	
	@Query("SELECT st "
		+ "FROM School sc "
		+ "INNER JOIN sc.periods pe "
		+ "INNER JOIN pe.classrooms cl "
		+ "INNER JOIN cl.students st "
		+ "WHERE sc.id = :id")
	List<Student> findStudentsById(Long id);
	
	@Query("SELECT cl "
		+ "FROM School sc "
		+ "INNER JOIN sc.periods pe "
		+ "INNER JOIN pe.classrooms cl "
		+ "WHERE sc.id = :id")
	List<Classroom> findClassroomsById(Long id);
	
	@Query("SELECT te "
		+ "FROM School sc "
		+ "INNER JOIN sc.periods pe "
		+ "INNER JOIN pe.classrooms cl "
		+ "INNER JOIN cl.disciplines di "
		+ "INNER JOIN di.teachers te "
		+ "WHERE sc.id = :id")
	List<Teacher> findTeachersById(Long id);
	
	@Query("SELECT di "
		+ "FROM School sc "
		+ "INNER JOIN sc.periods pe "
		+ "INNER JOIN pe.classrooms cl "
		+ "INNER JOIN cl.disciplines di "
		+ "WHERE sc.id = :id")
	List<Discipline> findDisciplinesById(Long id);
}
