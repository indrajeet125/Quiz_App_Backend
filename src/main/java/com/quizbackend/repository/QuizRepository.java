package com.quizbackend.repository;

import com.quizbackend.model.Question;
import com.quizbackend.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository  extends JpaRepository<Quiz, Long> {
}
