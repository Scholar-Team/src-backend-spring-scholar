package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

}
