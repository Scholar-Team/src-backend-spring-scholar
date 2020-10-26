package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
