package com.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scholar.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
