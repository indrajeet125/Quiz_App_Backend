package com.quizbackend.Controller;

import com.quizbackend.model.QuestionWrapper;
import com.quizbackend.model.Response;
import com.quizbackend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String Category, @RequestParam int numQ, @RequestParam String title) {
        System.out.println("create called ");
        return quizService.createQuiz(Category, numQ, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
return quizService.calculateResult(id, responses);
    }

}