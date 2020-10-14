package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.Telephone;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {

}
