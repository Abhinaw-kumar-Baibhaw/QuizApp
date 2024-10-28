package com.microservices.QuizProject.repo;

import com.microservices.QuizProject.model.QuizStart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<QuizStart,Integer> {
}
