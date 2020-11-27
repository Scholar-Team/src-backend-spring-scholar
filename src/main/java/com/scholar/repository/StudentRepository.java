package com.scholar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scholar.model.School;
import com.scholar.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT sc "
		+ "FROM Student st "
		+ "INNER JOIN st.classroom cl "
		+ "INNER JOIN cl.period pe "
		+ "INNER JOIN pe.school sc "
		+ "WHERE st.id = :id")
	Optional<School> findSchoolById(Long id);
}
