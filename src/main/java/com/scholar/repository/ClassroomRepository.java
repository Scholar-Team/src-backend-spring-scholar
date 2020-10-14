package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
