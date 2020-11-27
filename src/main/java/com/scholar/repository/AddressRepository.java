package com.scholar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scholar.model.Address;
import com.scholar.model.Person;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("SELECT pe "
		+ "FROM Person pe "
		+ "INNER JOIN pe.address ad "
		+ "WHERE ad.id = :id")
	Optional<Person> findPersonById(Long id);
}
