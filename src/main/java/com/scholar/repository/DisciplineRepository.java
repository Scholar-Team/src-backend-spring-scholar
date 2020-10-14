package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.Discipline;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

}
