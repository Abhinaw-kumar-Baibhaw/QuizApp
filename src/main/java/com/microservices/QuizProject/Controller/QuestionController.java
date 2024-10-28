package com.microservices.QuizProject.Controller;


import com.microservices.QuizProject.model.Quiz;
import com.microservices.QuizProject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("getAllQuestions")
    public ResponseEntity<List<Quiz>> getAllQuestion(){
        return questionService.findAllQuestion();
    }

    @GetMapping("getByCategory/{category}")
    public List<Quiz> getByCategory(@PathVariable String category){
        return questionService.findByCategory(category);
    }

    @PostMapping("addQuestion")
    public String addQuestion(@RequestBody Quiz  quiz){
        questionService.addQuestion(quiz);
        return "Success";
    }
}


