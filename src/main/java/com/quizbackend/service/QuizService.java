package com.quizbackend.service;

import com.quizbackend.model.Question;
import com.quizbackend.model.QuestionWrapper;
import com.quizbackend.model.Quiz;
import com.quizbackend.model.Response;
import com.quizbackend.repository.QuestionRepository;
import com.quizbackend.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    //1:22
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepository.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("created", HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById((long) id);
        List<Question> questionsFromDB = quiz.get().getQuestion();

        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }


        return new ResponseEntity<>(questionForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById((long) id).get();
        List<Question> questions = quiz.getQuestion();
        int right = 0, i = 0;
        for (Response response : responses) {
            if (response.equals(questions.get(i).getCorrectAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
