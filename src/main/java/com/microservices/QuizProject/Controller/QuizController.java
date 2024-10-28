package com.microservices.QuizProject.Controller;


import com.microservices.QuizProject.model.QuestionWrappper;
import com.microservices.QuizProject.model.Quiz;
import com.microservices.QuizProject.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }


    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrappper>> getQuizQuestions(@PathVariable Integer id){
          return    quizService.getQuizQuestions(id);
    }
}
