package com.microservices.QuizProject.service;


import com.microservices.QuizProject.model.Quiz;
import com.microservices.QuizProject.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<List<Quiz>> findAllQuestion() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public List<Quiz> findByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    public String addQuestion(Quiz quiz) {
        questionRepo.save(quiz);
        return  "success";
    }
}
