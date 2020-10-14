package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
