package com.scholar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scholar.model.Director;
import com.scholar.model.School;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

	@Query("SELECT sc "
		+ "FROM Director di "
		+ "INNER JOIN di.school sc "
		+ "WHERE di.id = :id")
	Optional<School> findSchoolById(Long id);
}
