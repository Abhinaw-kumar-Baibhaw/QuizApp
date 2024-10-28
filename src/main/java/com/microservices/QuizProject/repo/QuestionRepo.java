package com.microservices.QuizProject.repo;

import com.microservices.QuizProject.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepo extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByCategory(String category);

    @Query(value = "select * from quiz q where q.category=:category Order By RAND() LIMIT :numQ", nativeQuery = true)
    List<Quiz> findRandomQuestionsByCategory(String category, int numQ);
}
