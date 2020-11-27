package com.scholar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scholar.model.Classroom;
import com.scholar.model.Director;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

	@Query("SELECT di "
		+ "FROM Classroom cl "
		+ "INNER JOIN cl.period pe "
		+ "INNER JOIN pe.school sc "
		+ "INNER JOIN sc.director di "
		+ "WHERE cl.id = :id")
	Optional<Director> findDirectorById(Long id);
}
