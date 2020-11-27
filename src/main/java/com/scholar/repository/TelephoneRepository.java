package com.scholar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scholar.model.Person;
import com.scholar.model.Telephone;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {

	@Query("SELECT pe "
		+ "FROM Telephone te "
		+ "INNER JOIN te.person pe "
		+ "WHERE te.id = :id")
	Optional<Person> findPersonById(Long id);
}
