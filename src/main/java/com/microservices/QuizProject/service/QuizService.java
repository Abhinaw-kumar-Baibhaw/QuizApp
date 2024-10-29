package com.microservices.QuizProject.service;


import com.microservices.QuizProject.model.QuestionWrappper;
import com.microservices.QuizProject.model.Quiz;
import com.microservices.QuizProject.model.QuizStart;
import com.microservices.QuizProject.model.Response;
import com.microservices.QuizProject.repo.QuestionRepo;
import com.microservices.QuizProject.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Quiz> question = questionRepo.findRandomQuestionsByCategory(category,numQ);

        QuizStart quiz=new QuizStart();

        quiz.setTitle(title);

        quiz.setQuestions(question);

        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrappper>> getQuizQuestions(Integer id) {
        Optional<QuizStart> quiz = quizRepo.findById(id);
        List<Quiz> questions = quiz.get().getQuestions();
       List<QuestionWrappper> questionForUser = new ArrayList<>();
       for(Quiz q : questions){
           QuestionWrappper qw = new QuestionWrappper(q.getId(),q.getCategory(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           questionForUser.add(qw);
       }
       return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResponse(Integer id, List<Response> responses) {
        QuizStart quizStart = quizRepo.findById(id).get();
        List<Quiz> quizzes = quizStart.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
             if(response.getResponse().equals(quizzes.get(i).getRight_answer())){
                 right++;
                 i++;
             }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
