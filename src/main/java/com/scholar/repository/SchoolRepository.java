package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}
