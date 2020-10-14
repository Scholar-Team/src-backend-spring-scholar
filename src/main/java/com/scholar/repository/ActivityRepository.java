package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
