package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
